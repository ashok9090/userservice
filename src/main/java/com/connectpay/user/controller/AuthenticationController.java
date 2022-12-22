package com.connectpay.user.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.connectpay.user.Interface.UserInterface;
import com.connectpay.user.constant.Constant;
import com.connectpay.user.constant.TivreConstant;
import com.connectpay.user.entity.Login;
import com.connectpay.user.entity.RetailerMaster;
import com.connectpay.user.entity.StockiestMaster;
import com.connectpay.user.entity.SuperStockiestMaster;
import com.connectpay.user.request.AuthenticationRequest;
import com.connectpay.user.response.LoginResponse;
import com.connectpay.user.response.UserDetails;
import com.connectpay.user.secuirity.JWTTokenHelper;
import com.connectpay.user.secuirity.LoginDetails;
import com.connectpay.user.util.ManualPasswordEncoder;
import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@Slf4j
public class AuthenticationController {

	@Autowired
	private JWTTokenHelper jWTTokenHelper;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserInterface userInterface;
	@Autowired
	private ManualPasswordEncoder passwordEncoder;

	private Gson gson = new Gson();

	private static final Logger _cpLogs = LoggerFactory.getLogger(Constant.CP_LOGIN_LOGS);
	private static final String _logClassName = AuthenticationController.class.getSimpleName();

	@PostMapping("/authenticate")
	public ResponseEntity<LoginResponse> authenticate(HttpServletRequest request,
			@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		_cpLogs.info(_logClassName + "Request For Authentication Login " + gson.toJson(authenticationRequest));
		String jwtToken = "";
		LoginResponse response = new LoginResponse();
		try {
			final Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							authenticationRequest.getTerminalId() + ":" + authenticationRequest.getUserName(),
							authenticationRequest.getPassword()));
			_cpLogs.info(_logClassName + "Request For Authentication " + gson.toJson(authenticationRequest));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			LoginDetails loginDetails = (LoginDetails) authentication.getPrincipal();
			if (authenticationRequest.getDevicetype() != null) {
				if (!authenticationRequest.getDevicetype().isEmpty()) {
				} else {
					response.setError(Constant.DEVICETYPEBLANK);
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
				}
			} else {
				response.setError(Constant.DEVICETYPENULL);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}

			if (authenticationRequest.getDeviceId() != null) {
				if (!authenticationRequest.getDeviceId().isEmpty()) {
				} else {
					response.setError(Constant.DEVICEIDBLANK);
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
				}
			} else {
				response.setError(Constant.DEVICEIDNULL);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			jwtToken = jWTTokenHelper.generateToken(loginDetails.getUsername(), authenticationRequest);
			_cpLogs.info(_logClassName + "Request For Authentication " + gson.toJson(authenticationRequest));
			response.setToken(jwtToken);
			CompletableFuture<Boolean> future = userInterface.saveData(loginDetails, jwtToken, authenticationRequest,
					response);
			boolean otpRequired = future.get();

			if (otpRequired) {
				response.setDeviceChangeotpRequired(otpRequired);
				_cpLogs.info(_logClassName + "inside Otp Required " + gson.toJson(otpRequired));
				_cpLogs.info(_logClassName + "Response " + gson.toJson(response));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				boolean afterFixdaysOtpRequired = false;
				if (TivreConstant.OTPSENDROLE.stream()
						.anyMatch(p -> p.equalsIgnoreCase(loginDetails.getLogin().getRole()))) {
					CompletableFuture<Boolean> future1 = userInterface.checkForOtp(loginDetails, jwtToken,
							authenticationRequest, response);
					afterFixdaysOtpRequired = future1.get();
				}
				_cpLogs.info(_logClassName + "inside afterFixdaysOtpRequired " + gson.toJson(afterFixdaysOtpRequired));
				if (afterFixdaysOtpRequired) {
					response.setAfterFixdaysOtpRequired(afterFixdaysOtpRequired);
				}
				_cpLogs.info(_logClassName + "Response " + gson.toJson(response));
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}

		} catch (DisabledException e) {
			throw new Exception(Constant.USER_DISABLED, e);
		} catch (BadCredentialsException e) {
			throw new Exception(Constant.INVALID_CREDENTIALS, e);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception(Constant.INVALID_CREDENTIALS, e);
		}
	}

	@RequestMapping(value = "/refreshtoken", method = RequestMethod.GET)
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		String token = jWTTokenHelper.getToken(request);
		_cpLogs.info(_logClassName + " For Refresh Token =" + token);
		LoginResponse response = new LoginResponse();
		try {
			Map<String, Object> expectedMap = new HashMap<String, Object>();
			DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
			expectedMap = getMapFromIoJsonwebtokenClaims(claims);
			String deviceid = (String) expectedMap.get("DI");
			String username = (String) expectedMap.get("sub");
			_cpLogs.info(_logClassName + " Device Id =" + deviceid);
			String jwtToken = jWTTokenHelper.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
			response.setToken(jwtToken);
			CompletableFuture<LoginResponse> future = userInterface.allowForRefresh(deviceid, username, response);
			response = future.get();
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

	}

	public Map<String, Object> getMapFromIoJsonwebtokenClaims(Claims claims) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		if (claims != null)
			for (Entry<String, Object> entry : claims.entrySet()) {
				expectedMap.put(entry.getKey(), entry.getValue());
			}
		return expectedMap;
	}

	@GetMapping("/userinfo")
	public ResponseEntity<?> getUserInfo(Principal user) {
		log.info("user details " + user.getName());
		LoginDetails loginDetails = (LoginDetails) userDetailsService.loadUserByUsername(user.getName());
		Login login = loginDetails.getLogin();
		int cpid = loginDetails.getLogin().getCpID();
		try {
			if (login.getRole().equalsIgnoreCase(Constant.RETAILERROLE)) {
				CompletableFuture<RetailerMaster> future = userInterface.getRetailer(cpid);
				login.setRetailerMaster(future.get());
			} else if (login.getRole().equalsIgnoreCase(Constant.STOCKISTROLE)) {
				CompletableFuture<StockiestMaster> future = userInterface.getStockiestMaster(cpid);
				login.setStockiestMaster(future.get());
			} else if (login.getRole().equalsIgnoreCase(Constant.SUPERSTOCKISTROLE)) {
				CompletableFuture<SuperStockiestMaster> future = userInterface.getSuperStockiestMaster(cpid);
				login.setSuperStockiestMaster(future.get());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(login);
	}

	@GetMapping("/userExist")
	public ResponseEntity<?> userexist(Principal user) throws Exception {
		log.info("user details " + user.getName());
		try {
			LoginDetails loginDetails = (LoginDetails) userDetailsService.loadUserByUsername(user.getName());
			if (loginDetails != null) {
				return ResponseEntity.status(HttpStatus.OK).body(true);
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(false);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(Constant.INVALID_CREDENTIALS, e);
		}

	}

	@GetMapping("/loginDetails")
	public ResponseEntity<?> loginDetails(Principal user) {
		log.info("user details " + user.getName());
		LoginDetails loginDetails = (LoginDetails) userDetailsService.loadUserByUsername(user.getName());
		Login login = loginDetails.getLogin();
		return ResponseEntity.status(HttpStatus.OK).body(login);
	}

	@GetMapping("/getLoggedInId")
	public ResponseEntity<?> getLoggedInId(Principal user) {
		log.info("user details " + user.getName());
		LoginDetails loginDetails = (LoginDetails) userDetailsService.loadUserByUsername(user.getName());
		int cpid = loginDetails.getLogin().getCpID();
		return ResponseEntity.status(HttpStatus.OK).body(cpid);
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout(Principal user, @RequestHeader MultiValueMap<String, String> headers)
			throws Exception {
		String header = headers.getFirst(Constant.AUTHHEADER);
		String token = header.substring(7);
		log.info("logout  Token=" + token);
		LoginDetails loginDetails = (LoginDetails) userDetailsService.loadUserByUsername(user.getName());
		_cpLogs.info(_logClassName + " Logout call for user " + gson.toJson(loginDetails.getUsername()));
		final Claims claims = jWTTokenHelper.getAllClaimsFromToken(token);
		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String deviceid = (String) expectedMap.get("DI");

		CompletableFuture<Boolean> future = userInterface.logout(deviceid, loginDetails);
		try {
			return ResponseEntity.status(HttpStatus.OK).body(future.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	@PostMapping("/verifyUser")
	public ResponseEntity<?> verifyOtp(@RequestBody LoginResponse loginResponse) throws Exception {
		_cpLogs.info(_logClassName + " verifyUser " + gson.toJson(loginResponse));
		try {
			String token = loginResponse.getOtpToken();
			if (token == null) {
				throw new BadCredentialsException("OtpToken Can't be blank");
			} else {
				CompletableFuture<LoginResponse> future = userInterface.OtpVerification(loginResponse);
				loginResponse = future.get();
				_cpLogs.info(_logClassName + " OTP verify Status = " + gson.toJson(loginResponse));
				loginResponse.setDeviceId(null);
				loginResponse.setOtp(null);
				loginResponse.setOtpToken(null);
				if (loginResponse.getError() != null) {
					if (!loginResponse.getError().isEmpty()) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(loginResponse);
					} else {
						loginResponse.setAfterFixdaysOtpRequired(false);
						loginResponse.setDeviceChangeotpRequired(false);
						_cpLogs.info(_logClassName + " OTP verify Response = " + gson.toJson(loginResponse));
						return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
					}
				} else {
					loginResponse.setAfterFixdaysOtpRequired(false);
					loginResponse.setDeviceChangeotpRequired(false);
					_cpLogs.info(_logClassName + " OTP verify Response = " + gson.toJson(loginResponse));
					return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e);
		}
	}

	@PostMapping("/exposeApi")
	@CrossOrigin("*") // call ll come only from Wallet Service
	public ResponseEntity<?> getLoggedInIdForExposeApi(HttpServletRequest request,
			@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			CompletableFuture<UserDetails> cpid = userInterface.FindIdByAuthentication(authenticationRequest);
			return ResponseEntity.status(HttpStatus.OK).body(cpid.get());
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(Constant.INVALID_CREDENTIALS, e);
		}
	}

}
