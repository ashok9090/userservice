package com.connectpay.user.constant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TivreConstant {
	public static String CP_TIVRE_LOGS = "cpTIVRELogs";

	public static final String SMSURL = "https://sms.tivre.com/httppush/send_smsSch.asp?";
	public static final String DELIVERYURL = "https://sms.tivre.com/httppush/reporttxt.asp?";
	public static final String USERID = "rammohan@connectpay.in";
	public static final String PASSWORD = "Zillion1234*";
	public static final String SENDERID = "EMPLCP";
	public static final List<String> OTPSENDROLE = 
		    Collections.unmodifiableList(Arrays.asList("R","S","SS"));
	
	
	public static void main(String[] args) {
		System.err.println(OTPSENDROLE.stream().anyMatch(p->p.equalsIgnoreCase("PR")));
	}
	


}
