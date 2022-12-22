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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="token_management")
@JsonInclude(Include.NON_NULL)
@NamedQuery(name="TokenManagement.findAll", query="SELECT lkd FROM TokenManagement lkd")
public class TokenManagement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="userid",nullable = false)
	private String userId;
	
	@JsonIgnore
	@Column(name="password",nullable = false)
	private String password;
	
	@Column(name="token",length = 2024)
	private String token;
	
	@Column(name="create_token_date")
	private Date createTokenDate;
	
	@Column(name="refreshed_token_date")
	private Date refreshedTokenDate;
	
	@Column(name="login",nullable = false)
	private boolean login;
	
	@Column(name="logout",nullable = false)
	private boolean logout;
	
	@Column(name="app",nullable = false)
	private boolean app;
	
	@Column(name="device_name")
	private String deviceName;
	
	@Column(name="device_type")
	private String deviceType;
	
	@Column(name="device_id")
	private String deviceId;
	
	@Column(name="ip_address")
	private String ipAddress;
	
	@Column(name="lat")
	private String lat;
	
	@Column(name="lang")
	private String lang;
	
	
}
