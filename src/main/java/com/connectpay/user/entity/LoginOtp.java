package com.connectpay.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name="login_otp")
@NamedQuery(name="LoginOtp.findAll", query="SELECT lkd FROM LoginOtp lkd")
public class LoginOtp implements Serializable {
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int id;
		
		private String otp;
		
		@Column(name = "mobile_number")
		private String mobileNumber;
		
		@Column(name = "otp_date")
		private Date otpDate;
		
		@Column(name = "opt_success")
		private boolean optSuccess;
		
		@Column(name = "device_id")
		private String deviceId;
		
		@JsonIgnore
		@ManyToOne
		@JoinColumn(name = "login_id")
		private Login login;

		
		
		

}
