package com.connectpay.user.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class LoginResponse {
	
	private String token;
	
	private String error;
	
	private String message;
	
	private boolean deviceChangeotpRequired;
	
	private boolean afterFixdaysOtpRequired;
	
	private String otpToken;
	
	private String otp;
	
	private String deviceId;

}
