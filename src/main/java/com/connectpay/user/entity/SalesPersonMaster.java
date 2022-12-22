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

@Entity
@Table(name = "salesperson_master")
@NamedQuery(name = "SalesPersonMaster.findAll", query = "SELECT z FROM SalesPersonMaster z")
public class SalesPersonMaster implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String emailID;
	
	@Column(name = "altemailid")
	private String altEmailID;
	
	@Column(name = "mobile")
	private String mobile;

	@Column(name = "altmobile")
	private String altMobile;

	@Column(name = "village")
	private String village;
	
	@Column(name = "taluk")
	private String taluk;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "address")
	private String Address;
	
	@Column(name = "pincode")
	private String Pincode;
	
	@ManyToOne
	@JoinColumn(name = "cityid")
	private CityMaster cityMaster;
	
	@ManyToOne
	@JoinColumn(name = "countryid")
	private CountryMaster countryMaster;
	
	@ManyToOne
	@JoinColumn(name = "stateid")
	private StateMaster stateMaster;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "assigneddistrict")
	private String assignedDistrict;
	
	@ManyToOne
	@JoinColumn(name = "assignedstateid")
	private StateMaster assignedStateMaster;
	
	@Column(name = "pan")
	private String pan;
	
	@Column(name = "prefuserid")
	private String prefUserId;
	
	@ManyToOne
	@JoinColumn(name = "regionid")
	private RegionMaster regionMaster;
	
	@ManyToOne
	@JoinColumn(name = "amid")
	private AssistantManagerMaster assistantManagerMaster;
	
	@Column(name="createdBy")
	private String createdBy;
	
	@Column(name="createdDate")
	private Date createdDate;
	
	@Column(name="updatedBy")
	private String updatedBy;
	
	@Column(name="updatedDate")
	private Date updatedDate;
	
	@Column(name = "aadharnumber")
	private String aadharNumber;

	@Column(name = "gender", nullable=false)
	private String gender;
	
	@Column(name = "dol", nullable=false)
	private Date  dol;
		
	@Column(name = "status", nullable=false)
	private String status;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getAltEmailID() {
		return altEmailID;
	}

	public void setAltEmailID(String altEmailID) {
		this.altEmailID = altEmailID;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAltMobile() {
		return altMobile;
	}

	public void setAltMobile(String altMobile) {
		this.altMobile = altMobile;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getTaluk() {
		return taluk;
	}

	public void setTaluk(String taluk) {
		this.taluk = taluk;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPincode() {
		return Pincode;
	}

	public void setPincode(String pincode) {
		Pincode = pincode;
	}

	public CityMaster getCityMaster() {
		return cityMaster;
	}

	public void setCityMaster(CityMaster cityMaster) {
		this.cityMaster = cityMaster;
	}

	public CountryMaster getCountryMaster() {
		return countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	public StateMaster getStateMaster() {
		return stateMaster;
	}

	public void setStateMaster(StateMaster stateMaster) {
		this.stateMaster = stateMaster;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getPrefUserId() {
		return prefUserId;
	}

	public void setPrefUserId(String prefUserId) {
		this.prefUserId = prefUserId;
	}

	public RegionMaster getRegionMaster() {
		return regionMaster;
	}

	public void setRegionMaster(RegionMaster regionMaster) {
		this.regionMaster = regionMaster;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getAssignedDistrict() {
		return assignedDistrict;
	}

	public void setAssignedDistrict(String assignedDistrict) {
		this.assignedDistrict = assignedDistrict;
	}

	public StateMaster getAssignedStateMaster() {
		return assignedStateMaster;
	}

	public void setAssignedStateMaster(StateMaster assignedStateMaster) {
		this.assignedStateMaster = assignedStateMaster;
	}

	public Date getDol() {
		return dol;
	}

	public void setDol(Date dol) {
		this.dol = dol;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AssistantManagerMaster getAssistantManagerMaster() {
		return assistantManagerMaster;
	}

	public void setAssistantManagerMaster(AssistantManagerMaster assistantManagerMaster) {
		this.assistantManagerMaster = assistantManagerMaster;
	}


}
