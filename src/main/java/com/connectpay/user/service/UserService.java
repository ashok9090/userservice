package com.connectpay.user.service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.connectpay.user.Interface.UserInterface;
import com.connectpay.user.constant.Constant;
import com.connectpay.user.constant.TivreConstant;
import com.connectpay.user.controller.AuthenticationController;
import com.connectpay.user.entity.Login;
import com.connectpay.user.entity.LoginOtp;
import com.connectpay.user.entity.RetailerMaster;
import com.connectpay.user.entity.StockiestMaster;
import com.connectpay.user.entity.SuperStockiestMaster;
import com.connectpay.user.entity.TokenManagement;
import com.connectpay.user.entityservice.UserEntityServcie;
import com.connectpay.user.repository.LoginOtpRepo;
import com.connectpay.user.repository.LoginRepository;
import com.connectpay.user.repository.RetailerMasterRepository;
import com.connectpay.user.repository.StockiestMasterRepository;
import com.connectpay.user.repository.SuperStockiestMasterRepository;
import com.connectpay.user.repository.TokenManagementRepo;
import com.connectpay.user.request.AuthenticationRequest;
import com.connectpay.user.request.OTPToken;
import com.connectpay.user.response.LoginResponse;
import com.connectpay.user.response.UserDetails;
import com.connectpay.user.secuirity.LoginDetails;
import com.connectpay.user.util.ManualPasswordEncoder;
import com.connectpay.user.util.SMSUtility;
import com.google.gson.Gson;

@Service
public class UserService implements UserInterface {


	@Autowired
	private ManualPasswordEncoder manualPasswordEncoder;


	@Autowired
	private RetailerMasterRepository retailerMasterRepository;

	@Autowired
	private StockiestMasterRepository stockiestMasterRepository;

	@Autowired
	private SuperStockiestMasterRepository superStockiestMasterRepository;

	@Autowired
	private TokenManagementRepo tokenManagementRepo;

	@Autowired
	private SMSUtility smsUtility;
	
	@Autowired
	private LoginOtpRepo loginOtpRepo;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private UserEntityServcie  entityServcie;
	

	Gson gson = new Gson();

	private static final Logger _cpLogs = LoggerFactory.getLogger(Constant.CP_LOGIN_LOGS);
	private static final String _logClassName = AuthenticationController.class.getSimpleName();

	public Login saveUser(Login user) {
		return loginRepository.save(user);
	}

	@Override
	public Login createUser(Login user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompletableFuture<RetailerMaster> getRetailer(int cpid) throws Exception {
		return CompletableFuture.completedFuture(retailerMasterRepository.findById(cpid));
	}

	@Override
	public CompletableFuture<StockiestMaster> getStockiestMaster(int cpid) throws Exception {
		return CompletableFuture.completedFuture(stockiestMasterRepository.findById(cpid));
	}

	@Override
	public CompletableFuture<SuperStockiestMaster> getSuperStockiestMaster(int cpid) throws Exception {
		// TODO Auto-generated method stub
		return CompletableFuture.completedFuture(superStockiestMasterRepository.findById(cpid));
	}

	@Async("taskExecutor")
	@Override
	public CompletableFuture<Boolean> saveData(LoginDetails loginDetails, String jwtToken,
			AuthenticationRequest authenticationRequest, LoginResponse response) throws Exception {
		// TODO Auto-generated method stub
		boolean otpRequired = false;
		TokenManagement tokenManagement = tokenManagementRepo
				.findByDeviceIdAndUserId(authenticationRequest.getDeviceId(), loginDetails.getUsername());
        OTPToken otpToken=new OTPToken(loginDetails.getUsername(), authenticationRequest.getDevicetype(), 
        		authenticationRequest.getDeviceId(),jwtToken,loginDetails.getLogin().getId(),new Date()); 
       String otptoken= manualPasswordEncoder.urlencrypt(gson.toJson(otpToken));
       if(TivreConstant.OTPSENDROLE.stream().anyMatch(p->p.equalsIgnoreCase(loginDetails.getLogin().getRole()))) {
		if (null == tokenManagement) {
			otpRequired = true;
			tokenManagement = new TokenManagement();
			_cpLogs.info(_logClassName + "<<-----------------OTP Sent for Device change 1st time ----------------->>");
			this.sendOtp(loginDetails.getLogin(), Constant.FIRSTTIMELOGINOTPTEMPLATE,authenticationRequest.getDeviceId());
		} else {
			LoginOtp loginOtp=loginOtpRepo.findByLoginIdAndDeviceId(loginDetails.getLogin().getId(), authenticationRequest.getDeviceId());
			if (loginOtp != null) {
				if (!loginOtp.isOptSuccess()) {
					otpRequired = true;
					_cpLogs.info(_logClassName
							+ "<<-----------------OTP Sent for Device change 1st time----------------->>");
					this.sendOtp(loginDetails.getLogin(), Constant.FIRSTTIMELOGINOTPTEMPLATE,authenticationRequest.getDeviceId());
				}
			} else {
				otpRequired = true;
				_cpLogs.info(
						_logClassName + "<<-----------------OTP Sent for Device change 1st time----------------->>");
				this.sendOtp(loginDetails.getLogin(), Constant.FIRSTTIMELOGINOTPTEMPLATE,authenticationRequest.getDeviceId());
			}
		}
       }
		Date date = new Date();
		if (null == tokenManagement) {
			tokenManagement = new TokenManagement();
		}
		tokenManagement.setUserId(loginDetails.getUsername());
		tokenManagement.setCreateTokenDate(date);
		tokenManagement.setDeviceId(authenticationRequest.getDeviceId());
		tokenManagement.setDeviceType(authenticationRequest.getDevicetype());
		tokenManagement.setIpAddress(authenticationRequest.getIpAddress());
		tokenManagement.setDeviceName(authenticationRequest.getDeviceName());
		tokenManagement.setToken(jwtToken);
		tokenManagement.setLang(authenticationRequest.getLangitude());
		tokenManagement.setLat(authenticationRequest.getLatitude());
		tokenManagement.setPassword(loginDetails.getPassword());
		tokenManagement.setLogin(true);
		tokenManagement.setLogout(false);
		if (authenticationRequest.getDevicetype().equalsIgnoreCase(Constant.DEVICETYPEAPP))
			tokenManagement.setApp(true);
		tokenManagement = tokenManagementRepo.saveAndFlush(tokenManagement);
		if(otpRequired) {
			response.setToken(null);
			response.setOtpToken(otptoken);
		}
		_cpLogs.info(_logClassName + "<<-----------------TokenManagement Saved Successfully----------------->>"
				+ gson.toJson(tokenManagement));
		return CompletableFuture.completedFuture(otpRequired);
	}

	@Async("taskExecutor")
	@Override
	public CompletableFuture<Boolean> checkForOtp(LoginDetails loginDetails,String jwtToken, AuthenticationRequest authenticationRequest,LoginResponse response) throws Exception {
		// TODO Auto-generated method stub
		Login csLogin = loginDetails.getLogin();
		Date date = new Date();
		long diff = 0;
		boolean otpRequired = false;
		LoginOtp loginOtp=loginOtpRepo.findByLoginIdAndDeviceId(loginDetails.getLogin().getId(), authenticationRequest.getDeviceId());
		if (loginOtp.getOtpDate() != null)
			diff = (date.getTime() - loginOtp.getOtpDate().getTime()) / (60 * 60 * 1000);
		_cpLogs.info(_logClassName + "<<-----------------OTP time Diff ----------------->>" + diff + " Constant Hr="
				+ Constant.LOGINOTPTIMIMG);
		if (diff > Constant.LOGINOTPTIMIMG) {
			_cpLogs.info(_logClassName + "<<-----------------OTP Sent for Device change After ----------------->>"
					+ Constant.LOGINOTPTIMIMG);
			OTPToken otpToken=new OTPToken(loginDetails.getUsername(), authenticationRequest.getDevicetype(), 
	        		authenticationRequest.getDeviceId(),jwtToken,csLogin.getId(),new Date()); 
	       String otptoken= manualPasswordEncoder.urlencrypt(gson.toJson(otpToken));
	        response.setToken("");
			response.setOtpToken(otptoken);
			this.sendOtp(csLogin, Constant.HOURLOGINOTPTEMPLATE,authenticationRequest.getDeviceId());
			otpRequired = true;
		}

		return CompletableFuture.completedFuture(otpRequired);
	}

	private void sendOtp(Login csLogin, String firsttimeloginotptemplate,String deviceId) {
		// TODO Auto-generated method stub
		try {
			smsUtility.sendOtpForLogin(csLogin, firsttimeloginotptemplate,deviceId);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	@Async("taskExecutor")
	public CompletableFuture<Boolean> loginotp(String pw, int  loginId,String deviceId) {
		// TODO Auto-generated method stub
		boolean returnvalue=false;
		LoginOtp loginOtp=loginOtpRepo.findByLoginIdAndDeviceId(loginId, deviceId);
		if(loginOtp!=null) {
			if(loginOtp.getOtp().equalsIgnoreCase(pw)) {
				returnvalue=true;
				loginOtp.setOptSuccess(true);
				loginOtp.setOtpDate(new Date());
				loginOtpRepo.save(loginOtp);
			}
		}
		return CompletableFuture.completedFuture(returnvalue);
	}

	@Override
	public CompletableFuture<LoginResponse> otpVerification(LoginResponse loginResponse) throws Exception {
		// TODO Auto-generated method stub
		OTPToken otpToken=new OTPToken();
		try {
		String otktoken=manualPasswordEncoder.urldecrypt(loginResponse.getOtpToken());
		otpToken=gson.fromJson(otktoken, OTPToken.class);
		}catch (Exception e) {
			// TODO: handle exception
			throw new Exception("OTP Token Not valid ",e);
		}
		
		if(loginResponse.getDeviceId()!=null) {
			if(!loginResponse.getDeviceId().isEmpty()) {
				if(loginResponse.getDeviceId().equalsIgnoreCase(otpToken.getDeviceId())) {
					Date date=new Date();
					Date otpCreateddate=otpToken.getCreatedDateTime();
					long diff = (date.getTime() - otpCreateddate.getTime()) / (60 * 1000);
					if(diff<=Constant.OTPTIME) {
				CompletableFuture<Boolean> completableFuture=this.loginotp(loginResponse.getOtp(), otpToken.getId(),loginResponse.getDeviceId());
				if(completableFuture.get()) {
					loginResponse.setToken(otpToken.getToken());
				}else {
					loginResponse.setError(Constant.OTPMISMATCH);
				}
				}else {
					loginResponse.setError(Constant.OTPTIMIMEEXPIRE);	
				}
				return CompletableFuture.completedFuture(loginResponse);
				}else {
					loginResponse.setError(Constant.DEVICEIDMISMATCH);
					return CompletableFuture.completedFuture(loginResponse);
				}
			}else {
				loginResponse.setError(Constant.DEVICEIDBLANK);
				return CompletableFuture.completedFuture(loginResponse);
			}
		}else {
			loginResponse.setError(Constant.DEVICEIDNULL);
			return CompletableFuture.completedFuture(loginResponse);
		}
	}
	
	@Async("taskExecutor")
	@Override
	public CompletableFuture<Boolean> logout(String deviceId, LoginDetails loginDetails) {
		// TODO Auto-generated method stub
		TokenManagement tokenManagement=tokenManagementRepo.findByDeviceIdAndUserId(deviceId, loginDetails.getUsername());
		_cpLogs.info(_logClassName+" Logout call for user "+loginDetails.getUsername()+ " Device Id="+tokenManagement.getDeviceId());
		if(tokenManagement!=null) {
		tokenManagement.setToken("");
		tokenManagement.setLogout(true);
		tokenManagement.setLogin(false);
		tokenManagement=tokenManagementRepo.saveAndFlush(tokenManagement);
		}
		return CompletableFuture.completedFuture(true);
	}

	@Async("taskExecutor")
	@Override
	public CompletableFuture<LoginResponse> allowForRefresh(String deviceId, String username,LoginResponse response) throws Exception{
		// TODO Auto-generated method stub
		TokenManagement tokenManagement=tokenManagementRepo.findByDeviceIdAndUserId(deviceId,username);
		if(tokenManagement!=null) {
			
		if(tokenManagement.isLogout()){
			response.setError(Constant.TOKENLOGOUT);
			response.setToken(null);
		}else if(!tokenManagement.isLogin()) {
			response.setError(Constant.TOKENNOTLOGGGEDIN);
			response.setToken(null);
		}else {
			tokenManagement.setToken(response.getToken());
			tokenManagement.setRefreshedTokenDate(new Date());
			tokenManagementRepo.saveAndFlush(tokenManagement);
		}
		}
		return CompletableFuture.completedFuture(response);
	}

	@Async("taskExecutor")
	@Override
	public CompletableFuture<Boolean> checkForLogout(String deviceId,String userName) {
		// TODO Auto-generated method stub
		boolean returnflag=false;
		TokenManagement tokenManagement=tokenManagementRepo.findByDeviceIdAndUserId(deviceId, userName);
		if(tokenManagement==null) {
			returnflag=true;
		}else if(tokenManagement.isLogout()){
			returnflag=true;
		}else {
			returnflag=false;
		}
		return CompletableFuture.completedFuture(returnflag);
	}

	/*
	 * @Async("taskExecutor")
	 * 
	 * @Override public CompletableFuture<Login> getUser(Long userId, Login user)
	 * throws Exception { // TODO Auto-generated method stub CompletableFuture<User>
	 * completableFuture=userService.findById(userId); user=completableFuture.get();
	 * return CompletableFuture.completedFuture(user); }
	 */
	@Async("taskExecutor")
	@Override
	public CompletableFuture<UserDetails> findIdByAuthentication(AuthenticationRequest authenticationRequest) throws Exception{
		// TODO Auto-generated method stub
		Login login=loginRepository.findByTerminalid(authenticationRequest.getTerminalId());
		int rid=0;
		int sid=0;
		int ssid=0;
		if(login!=null) {
		if(login.getRole().equalsIgnoreCase(Constant.RETAILERROLE)) {
			rid=login.getCpID();
			
			sid=retailerMasterRepository.findStockiestMasterIdById(rid);
			
			ssid=retailerMasterRepository.findSuperStockiestMasterIdById(rid);
			
		}else if(login.getRole().equalsIgnoreCase(Constant.STOCKISTROLE)) {
			sid=login.getCpID();
			ssid=stockiestMasterRepository.findSSID(sid);
		}else if(login.getRole().equalsIgnoreCase(Constant.SUPERSTOCKISTROLE)) {
			ssid=login.getCpID();
		}
		UserDetails details=new UserDetails(login.getRole(),rid, sid,ssid);
		return CompletableFuture.completedFuture(details);
		}else {
			throw new Exception(Constant.INVALID_CREDENTIALS);
		}
		
		
		
	}
	

}
