package com.connectpay.user.Interface;

import java.util.concurrent.CompletableFuture;

import com.connectpay.user.entity.Login;
import com.connectpay.user.entity.RetailerMaster;
import com.connectpay.user.entity.StockiestMaster;
import com.connectpay.user.entity.SuperStockiestMaster;
import com.connectpay.user.request.AuthenticationRequest;
import com.connectpay.user.response.LoginResponse;
import com.connectpay.user.response.UserDetails;
import com.connectpay.user.secuirity.LoginDetails;

public interface UserInterface {

	Login saveUser(Login user)throws Exception;
	Login createUser(Login user)throws Exception;
	CompletableFuture<RetailerMaster> getRetailer(int cpid)throws Exception;
	CompletableFuture<StockiestMaster> getStockiestMaster(int cpid)throws Exception;
	CompletableFuture<SuperStockiestMaster> getSuperStockiestMaster(int cpid)throws Exception;
	CompletableFuture<Boolean> saveData(LoginDetails loginDetails, String jwtToken, AuthenticationRequest authenticationRequest, LoginResponse response) throws Exception;
	CompletableFuture<Boolean> checkForOtp(LoginDetails loginDetails,String jwtToken, AuthenticationRequest authenticationRequest, LoginResponse response)throws Exception;
	CompletableFuture<LoginResponse> OtpVerification(LoginResponse loginResponse)throws Exception;
	CompletableFuture<Boolean> logout(String token, LoginDetails loginDetails);
	CompletableFuture<LoginResponse> allowForRefresh(String deviceid, String token, LoginResponse response) throws Exception;
	CompletableFuture<Boolean> checkForLogout(String deviceId,String userName);
	CompletableFuture<UserDetails> FindIdByAuthentication(AuthenticationRequest authenticationRequest)throws Exception;
	

}
