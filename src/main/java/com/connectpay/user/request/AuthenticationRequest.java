package com.connectpay.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
	private String terminalId;
	private String userName;
	private String password;
	private String devicetype;
	private String deviceId;
	private String ipAddress;
	private String langitude;
	private String latitude;
	private String deviceName;
	
}
