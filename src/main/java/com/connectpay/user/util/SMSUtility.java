package com.connectpay.user.util;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.connectpay.user.constant.Constant;
import com.connectpay.user.controller.AuthenticationController;
import com.connectpay.user.entity.Login;
import com.connectpay.user.entity.LoginOtp;
import com.connectpay.user.repository.LoginOtpRepo;
import com.connectpay.user.repository.RetailerMasterRepository;
import com.connectpay.user.repository.StockiestMasterRepository;
import com.connectpay.user.repository.SuperStockiestMasterRepository;

@Service
public class SMSUtility {
	
	@Autowired
	private LoginOtpRepo loginOtpRepo;
	
	@Autowired
	private RetailerMasterRepository rRepo;

	@Autowired
	private StockiestMasterRepository sRepo;

	@Autowired
	private SuperStockiestMasterRepository ssRepo;
	
	private static final Logger _cpLogs = LoggerFactory.getLogger(Constant.CP_LOGIN_LOGS);
    private static final String _logClassName = AuthenticationController.class.getSimpleName();

	public void sendOtpForLogin(Login login, String firsttimeloginotptemplate,String deviceId) {
		// TODO Auto-generated method stub
		 if(login!=null) {
				String mob="";
				LoginOtp otp=loginOtpRepo.findByLoginIdAndDeviceId(login.getId(),deviceId);
				if(otp==null)
					otp=new LoginOtp();
				 _cpLogs.info(_logClassName+ "<<----------------- Start method sendOtpForLogin ----------------->>");
				 otp.setLogin(login);
				 
				_cpLogs.info(_logClassName+ "<<----------------- ROLE OF THIS USER = ----------------->>"+login.getRole());
				if(login.getRole().equalsIgnoreCase(Constant.SROLE)) {
					String mobileNumber=sRepo.findMobileById(login.getCpID());
					_cpLogs.info(_logClassName+ "<<-----------------StockiestMaster Mobile Number = ----------------->>"+mobileNumber);
		    			mob=mobileNumber;
		    		    otp.setMobileNumber(mob);
				}else if(login.getRole().equalsIgnoreCase(Constant.SSROLE)){
					String mobileNumber=ssRepo.findMobileById(login.getCpID());
		    		_cpLogs.info(_logClassName+ "<<-----------------SuperStockiestMaster = ----------------->>"+mobileNumber);
		    			mob=mobileNumber;
		    		    otp.setMobileNumber(mob);
		    	}else if(login.getRole().equalsIgnoreCase(Constant.RETAILERROLE)){
		    		String mobileNumber=rRepo.findMobileById(login.getCpID());
		    		_cpLogs.info(_logClassName+ "<<-----------------RetailerMaster = ----------------->>"+mobileNumber);
		    			mob=mobileNumber;
		    			otp.setMobileNumber(mob);
		    	}else {
		    		_cpLogs.info(_logClassName+ "<<-----------------USER ROLE NOT FOUND = ----------------->>"+login.getRole());
				/* throw new CPServiceException("USER ROLE NOT FOUND"); */
		    	}
		       	
		       	if(mob !="") {
		       	 _cpLogs.info(_logClassName+ "<<-----------------inside mobile is not blank ----------------->>"+mob);
		       		if(mob.length()!=12) {
		       			mob="91"+mob;
		       		}
		       		_cpLogs.info(_logClassName+ "<<-----------------final Mobile Number ----------------->>"+mob);
					Sms sms = new Sms(mob,firsttimeloginotptemplate);
					_cpLogs.info(_logClassName+ "<<-----------------final OTP ----------------->>"+mob+"    "+sms.getOtp());
					otp.setOtp(sms.getOtp());
					otp.setOtpDate(new Date());
					otp.setOptSuccess(false);
					otp.setDeviceId(deviceId);
					loginOtpRepo.save(otp);
					TivreSmsService smsService = new TivreSmsService();
					if(login.isLoginOtpControl())
					smsService.sendSms(sms);
		        _cpLogs.info(_logClassName+ "<<-----------------sendOtp method END ----------------->>"+mob); 
			}
				 }
			}
			@Async
			public CompletableFuture<Boolean> loginotp(String pw, Login login,String deviceId) {
				// TODO Auto-generated method stub
				boolean returnvalue=false;
				LoginOtp loginOtp=loginOtpRepo.findByLoginIdAndDeviceId(login.getId(),deviceId);
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

}
