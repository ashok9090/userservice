package com.connectpay.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
@Entity
@Table(name="cp_login")
@JsonInclude(Include.NON_NULL)
@Data
@NamedQuery(name="Login.findAll", query="SELECT lkd FROM Login lkd")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@NotNull
	@Column(name="role")
	private String role;
	
	@NotNull
	@Column(name="terminalid")
	private String terminalid;
	
	
	@NotNull
	@Column(name="userid")
	private String userID;
	
	@JsonIgnore
	@NotNull
	@Column(name="password")
	private String password;
	
	@NotNull
	@Column(name="cpid")
	private int cpID;
	
	@Column(name="createdBy")
	private String createdBy;
	
	@Column(name="createdDate")
	private Date createdDate;
	
	@Column(name="updatedBy")
	private String updatedBy;
	
	@Column(name="updatedDate")
	private Date updatedDate;

	@Column(name="forgotpwdstatus",nullable=false)
	private String forgotpwdstatus;
	
	@Column(name="enabled",columnDefinition = "boolean default true")
	private boolean enabled=true;
	
	@Column(name="multiple_attempt_block",nullable=false)
	private boolean multipleAttemptBlock;
	
	@Column(name="login_monitor",nullable=false)
	private boolean loginMonitor;
	
	@Column(name="account_disabled_enabled_date")
	private Date accountDisabledEnabledDate;
	
	@Column(name="login_otp_control",columnDefinition = "boolean default true")
	private boolean loginOtpControl=true;
	
	@Transient
	private RetailerMaster retailerMaster;
	
	@Transient
	private StockiestMaster stockiestMaster;
	
	@Transient
	private SuperStockiestMaster superStockiestMaster;

	
	
	

}
