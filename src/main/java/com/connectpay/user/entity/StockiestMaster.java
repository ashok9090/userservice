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

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "stockiest_master")
@DynamicUpdate
@NamedQuery(name = "StockiestMaster.findAll", query = "SELECT z FROM StockiestMaster z")
public class StockiestMaster implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "outletname")
	private String outletName;
	
	@Column(name = "emailid")
	private String emailID;
	
/*	@Column(name = "phone")
	private String phone;
*/	
	@Column(name = "mobile")
	private String mobile;
	
	@Column(name = "natbusiness")
	private String natBusiness;
	
/*	@Column(name = "village")
	private String village;
	
	@Column(name = "taluk")
	private String taluk;
*/	
	@Column(name = "district")
	private String district;
	
	@Column(name = "officeaddress")
	private String officeAddress;
	
	@Column(name = "officepincode")
	private String officePincode;
	
	@ManyToOne
	@JoinColumn(name = "officecity")
	private CityMaster cityMaster;
	
	@ManyToOne
	@JoinColumn(name = "officecountryid")
	private CountryMaster countryMaster;
	
	@ManyToOne
	@JoinColumn(name = "officestateid")
	private StateMaster stateMaster;
	
/*	@ManyToOne
	@JoinColumn(name = "officeairportcity")
	private CityMaster airportCityMaster;
*/	
	@ManyToOne
	@JoinColumn(name = "regionid")
	private RegionMaster regionMaster;

	@ManyToOne
	@JoinColumn(name = "salespersonid")
	private SalesPersonMaster salesPersonMaster;
	
	@Column(name = "peraddress")
	private String perAddress;
	
	@Column(name = "perpincode")
	private String perPincode;
	
	@ManyToOne
	@JoinColumn(name = "percity")
	private CityMaster perCityMaster;
	
	@ManyToOne
	@JoinColumn(name = "percountryid")
	private CountryMaster percountryMaster;
	
	@ManyToOne
	@JoinColumn(name = "perstateid")
	private StateMaster perStateMaster;

	@Column(name = "resaddress")
	private String resAddress;
	
	@Column(name = "respincode")
	private String resPincode;
	
	@ManyToOne
	@JoinColumn(name = "rescity")
	private CityMaster resCityMaster;
	
	@ManyToOne
	@JoinColumn(name = "rescountryid")
	private CountryMaster resCountryMaster;
	
	@ManyToOne
	@JoinColumn(name = "resstateid")
	private StateMaster resStateMaster;

	@Column(name = "perlandmark")
	private String perLandmark;
	
	@Column(name = "reslandmark")
	private String resLandmark;

	@Column(name = "officelandmark")
	private String officeLandmark;

	@ManyToOne
	@JoinColumn(name = "cppackageid")
	private CPPackageMaster cpPackageMaster;
	
	@Column(name = "onetimecost")
	private double oneTimeCost;
	
	@Column(name = "refundamount")
	private double refundAmount;
	
	@ManyToOne
	@JoinColumn(name = "ssid")
	private SuperStockiestMaster superStockiestMaster;
	
	@Column(name = "pan")
	private String pan;
	
	@Column(name = "contactperson")
	private String contactPerson;
	
	@Column(name = "contactpersonno")
	private String contactPersonNo;
	
	@Column(name = "approved")
	private String approved;
	
	@Column(name = "approvedby")
	private String approvedby;
	
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss a")
	@Column(name="appdate")
	private Date appdate;

	
	@Column(name = "kycstatus")
	private String kycStatus;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "status")
	private String status;
	
	@Column(name="createdBy")
	private String createdBy;
	
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss a")
	@Column(name="createdDate")
	private Date createdDate;
	
	
	@Column(name="updatedBy")
	private String updatedBy;
	
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss a")
	
	@Column(name="updatedDate")
	private Date updatedDate;

	@Column(name = "prefuserid")
	private String prefUserId;
	
	@Column(name = "altmobile")
	private String altMobile;
	
	@Column(name = "gstnumber")
	private String gstNumber;
	
	@Column(name = "aadharnumber")
	private String aadharNumber;
	
	/*
	 * @Column(name = "wbalance",nullable=false) private double wBalance;
	 * 
	 * @Column(name = "abalance",nullable=false) private double aBalance;
	 */
	
	@Column(name = "paymenttype",nullable=false)
	private String paymenttype;

	@Column(name = "paymentid",nullable=false)
	private String paymentid;

	
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss a")
	@Column(name = "paydate",nullable=false)
	private Date paydate;

	@Column(name = "payamount",nullable=false)
	private double payamount;

	@Column(name = "paymobile",nullable=false)
	private String paymobile;

	@Column(name = "payatmid",nullable=false)
	private String payatmid;

	@Column(name = "depbank",nullable=false)
	private String depbank;

	@Column(name = "terminalid",nullable=false)
	private String terminalid;

	@Column(name = "totterminal")
	private int totterminal;
	
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss a")
	@Column(name = "fcleardate",nullable=false)
	private Date fcleardate;

	@Column(name = "terminalbalance",nullable=false)
	private int terminalbalance;

	@Column(name = "pkgdiscount",nullable=false)
	private double pkgdiscount;

	@Column(name = "pkgtype",nullable=false)
	private String pkgtype;

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

	public String getOutletName() {
		return outletName;
	}

	public void setOutletName(String outletName) {
		this.outletName = outletName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	/*public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
*/
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getNatBusiness() {
		return natBusiness;
	}

	public void setNatBusiness(String natBusiness) {
		this.natBusiness = natBusiness;
	}
	
	/*public String getVillage() {
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
*/
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getOfficePincode() {
		return officePincode;
	}

	public void setOfficePincode(String officePincode) {
		this.officePincode = officePincode;
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

	/*public CityMaster getAirportCityMaster() {
		return airportCityMaster;
	}

	public void setAirportCityMaster(CityMaster airportCityMaster) {
		this.airportCityMaster = airportCityMaster;
	}
*/
	public RegionMaster getRegionMaster() {
		return regionMaster;
	}

	public void setRegionMaster(RegionMaster regionMaster) {
		this.regionMaster = regionMaster;
	}

	public SalesPersonMaster getSalesPersonMaster() {
		return salesPersonMaster;
	}

	public void setSalesPersonMaster(SalesPersonMaster salesPersonMaster) {
		this.salesPersonMaster = salesPersonMaster;
	}

	public String getPerAddress() {
		return perAddress;
	}

	public void setPerAddress(String perAddress) {
		this.perAddress = perAddress;
	}

	public String getPerPincode() {
		return perPincode;
	}

	public void setPerPincode(String perPincode) {
		this.perPincode = perPincode;
	}

	public CityMaster getPerCityMaster() {
		return perCityMaster;
	}

	public void setPerCityMaster(CityMaster perCityMaster) {
		this.perCityMaster = perCityMaster;
	}

	public CountryMaster getPercountryMaster() {
		return percountryMaster;
	}

	public void setPercountryMaster(CountryMaster percountryMaster) {
		this.percountryMaster = percountryMaster;
	}

	public StateMaster getPerStateMaster() {
		return perStateMaster;
	}

	public void setPerStateMaster(StateMaster perStateMaster) {
		this.perStateMaster = perStateMaster;
	}

	public String getResAddress() {
		return resAddress;
	}

	public void setResAddress(String resAddress) {
		this.resAddress = resAddress;
	}

	public String getResPincode() {
		return resPincode;
	}

	public void setResPincode(String resPincode) {
		this.resPincode = resPincode;
	}

	public CityMaster getResCityMaster() {
		return resCityMaster;
	}

	public void setResCityMaster(CityMaster resCityMaster) {
		this.resCityMaster = resCityMaster;
	}

	public CountryMaster getResCountryMaster() {
		return resCountryMaster;
	}

	public void setResCountryMaster(CountryMaster resCountryMaster) {
		this.resCountryMaster = resCountryMaster;
	}

	public StateMaster getResStateMaster() {
		return resStateMaster;
	}

	public void setResStateMaster(StateMaster resStateMaster) {
		this.resStateMaster = resStateMaster;
	}

	public String getPerLandmark() {
		return perLandmark;
	}

	public void setPerLandmark(String perLandmark) {
		this.perLandmark = perLandmark;
	}

	public String getResLandmark() {
		return resLandmark;
	}

	public void setResLandmark(String resLandmark) {
		this.resLandmark = resLandmark;
	}

	public String getOfficeLandmark() {
		return officeLandmark;
	}

	public void setOfficeLandmark(String officeLandmark) {
		this.officeLandmark = officeLandmark;
	}

	public CPPackageMaster getCpPackageMaster() {
		return cpPackageMaster;
	}

	public void setCpPackageMaster(CPPackageMaster cpPackageMaster) {
		this.cpPackageMaster = cpPackageMaster;
	}

	public double getOneTimeCost() {
		return oneTimeCost;
	}

	public void setOneTimeCost(double oneTimeCost) {
		this.oneTimeCost = oneTimeCost;
	}

	public double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public SuperStockiestMaster getSuperStockiestMaster() {
		return superStockiestMaster;
	}

	public void setSuperStockiestMaster(SuperStockiestMaster superStockiestMaster) {
		this.superStockiestMaster = superStockiestMaster;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPersonNo() {
		return contactPersonNo;
	}

	public void setContactPersonNo(String contactPersonNo) {
		this.contactPersonNo = contactPersonNo;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getKycStatus() {
		return kycStatus;
	}

	public void setKycStatus(String kycStatus) {
		this.kycStatus = kycStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getAltMobile() {
		return altMobile;
	}

	public void setAltMobile(String altMobile) {
		this.altMobile = altMobile;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	/*
	 * public double getwBalance() { return wBalance; }
	 * 
	 * public void setwBalance(double wBalance) { this.wBalance = wBalance; }
	 * 
	 * public double getaBalance() { return aBalance; }
	 * 
	 * public void setaBalance(double aBalance) { this.aBalance = aBalance; }
	 */

	public String getPaymenttype() {
		return paymenttype;
	}

	public void setPaymenttype(String paymenttype) {
		this.paymenttype = paymenttype;
	}

	public String getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	public double getPayamount() {
		return payamount;
	}

	public void setPayamount(double payamount) {
		this.payamount = payamount;
	}

	public String getPaymobile() {
		return paymobile;
	}

	public void setPaymobile(String paymobile) {
		this.paymobile = paymobile;
	}

	public String getPayatmid() {
		return payatmid;
	}

	public void setPayatmid(String payatmid) {
		this.payatmid = payatmid;
	}

	public String getDepbank() {
		return depbank;
	}

	public void setDepbank(String depbank) {
		this.depbank = depbank;
	}

	public String getTerminalid() {
		return terminalid;
	}

	public void setTerminalid(String terminalid) {
		this.terminalid = terminalid;
	}

	public int getTotterminal() {
		return totterminal;
	}

	public void setTotterminal(int totterminal) {
		this.totterminal = totterminal;
	}

	public Date getFcleardate() {
		return fcleardate;
	}

	public void setFcleardate(Date fcleardate) {
		this.fcleardate = fcleardate;
	}

	public String getApprovedby() {
		return approvedby;
	}

	public void setApprovedby(String approvedby) {
		this.approvedby = approvedby;
	}

	public Date getAppdate() {
		return appdate;
	}

	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}

	public int getTerminalbalance() {
		return terminalbalance;
	}

	public void setTerminalbalance(int terminalbalance) {
		this.terminalbalance = terminalbalance;
	}

	public double getPkgdiscount() {
		return pkgdiscount;
	}

	public void setPkgdiscount(double pkgdiscount) {
		this.pkgdiscount = pkgdiscount;
	}

	public String getPkgtype() {
		return pkgtype;
	}

	public void setPkgtype(String pkgtype) {
		this.pkgtype = pkgtype;
	}
	
	
}
