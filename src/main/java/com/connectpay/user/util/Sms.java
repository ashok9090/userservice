package com.connectpay.user.util;


import java.text.DecimalFormat;

import com.connectpay.user.constant.Constant;

public class Sms {
	private String Userid;
	private String password;
	private String Msg;
	private String mobnum;
	private String senderid;
	private String msgid;
	private final String TivreID = "1";
	private final String qrytype = "Simple";
	private String otp;
	private ZillionUtils util = new ZillionUtils();
	private static DecimalFormat df = new DecimalFormat("#0.00");
	public Sms() {
	      String msg_random = util.generateRandomNumber(999999);	      
	      otp = util.generateRandomNumber(999999);
	      Userid = "rammohan@connectpay.in";
	      password = "Zillion1234*";
	      Msg = "The verification code for Forgot Password is  "+ otp + "  please do not share it with anyone -ConnectPay";
	      mobnum = "919901544766";
	      senderid = "TBSLTD";
	      msgid = ""+msg_random+"";
	}
	
	public Sms(String mobileNumber,String template) {
	      String msg_random = util.generateRandomNumber(999999);	      
	      otp = util.generateRandomNumber(999999);
	      Userid = "rammohan@connectpay.in";
	      password = "Zillion1234*";
	      if(template.equalsIgnoreCase(Constant.FIRSTTIMELOGINOTPTEMPLATE)) {
	    	  Msg="Dear partner, Welcome! please use this verification code - "+otp+" -ConnectPay";
	      }else if(template.equalsIgnoreCase(Constant.HOURLOGINOTPTEMPLATE)) {
	    	  Msg="Dear partner, please use this verification code  "+otp+" -ConnectPay";
	      }

	      mobnum = mobileNumber;
	      senderid = "EMPLCP";
	      msgid = ""+msg_random+"";
	}
	
	public Sms(String mobileNumber,String prodType,String msgvalue) {
	      String msg_random = util.generateRandomNumber(999999);	      
	       Userid = "rammohan@connectpay.in";
	      password = "Zillion1234*";
	      if(prodType.equalsIgnoreCase("BBPS")) {	  	    
		      Msg=msgvalue;
	      }
	      if(prodType.equalsIgnoreCase("DR")) {	  	    
		      Msg=msgvalue;
	      }
	      mobnum = mobileNumber;
	      senderid = "EMPLCP";
	      msgid = ""+msg_random+"";
	}
	public String getUserid() {
		return Userid;
	}
	public void setUserid(String userid) {
		Userid = userid;
	}
	public String getQrytype() {
		return qrytype;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	public String getMobnum() {
		return mobnum;
	}
	public void setMobnum(String mobnum) {
		this.mobnum = mobnum;
	}
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getTivreID() {
		return TivreID;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
}
