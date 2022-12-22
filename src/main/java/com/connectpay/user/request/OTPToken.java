package com.connectpay.user.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTPToken {
	
	private String userName;
	
	private String devicetype;
	
	private String deviceId;
	
	private String token;
	
	private int id;
	
	private Date createdDateTime;

}
