package com.connectpay.user.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "retailer_master")
@NamedQuery(name = "RetailerMaster.findAll", query = "SELECT z FROM RetailerMaster z")
public class RetailerMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "outletname")
	private String outletName;

	@Column(name = "emailid")
	private String emailID;

	/*
	 * @Column(name = "altemailid") private String altEmailID;
	 * 
	 * @Column(name = "phone") private String phone;
	 */
	@Column(name = "mobile")
	private String mobile;

	@Column(name = "altmobile")
	private String altMobile;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "IST")
	@Column(name = "dob")
	private Date dob;

	/*
	 * @Column(name = "dom") private String dom;
	 */
	@Column(name = "natbusiness")
	private String natBusiness;

	@Column(name = "epcpid")
	private int epcpid;

	/*
	 * @Column(name = "village") private String village;
	 * 
	 * @Column(name = "taluk") private String taluk;
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

	/*
	 * @Column(name = "typeoforg") private String typeOfOrg;
	 */
	@Column(name = "pan")
	private String pan;

	@Column(name = "officelandmark")
	private String officeLandmark;

	/*
	 * @Column(name = "nearbylocality") private String nearByLocality;
	 * 
	 * @Column(name = "nearbypincode") private String nearByPincode;
	 * 
	 * @Column(name = "carparking") private String carParking;
	 */
	@Column(name = "mainroad")
	private String mainRoad;

	/*
	 * @Column(name = "otherportal") private String otherPortal;
	 * 
	 * @Column(name = "otherportalname") private String otherPortalName;
	 */
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

	@Column(name = "reslandmark")
	private String resLandmark;

	@Column(name = "peraddress")
	private String perAddress;

	@Column(name = "perpincode")
	private String perPincode;

	@ManyToOne
	@JoinColumn(name = "percity")
	private CityMaster perCityMaster;

	@ManyToOne
	@JoinColumn(name = "percountryid")
	private CountryMaster perCountryMaster;

	@ManyToOne
	@JoinColumn(name = "perstateid")
	private StateMaster perStateMaster;

	@Column(name = "perlandmark")
	private String perLandmark;

	@ManyToOne
	@JoinColumn(name = "cppackageid")
	private CPPackageMaster cpPackageMaster;

	@Column(name = "prefuserid")
	private String prefUserId;

	@Column(name = "stockiestincentive")
	private double stockiestIncentive;

	@Column(name = "refferalincentive")
	private double refferalIncentive;

	@Column(name = "terminalincentive")
	private double terminalIncentive;

	@ManyToOne
	@JoinColumn(name = "regionid")
	private RegionMaster regionMaster;

	@Column(name = "referalid")
	private String referalId;

	@Column(name = "packagediscount")
	private String packagediscount;

	@ManyToOne
	@JoinColumn(name = "salespersonid")
	private SalesPersonMaster salesPersonMaster;

	@ManyToOne
	@JoinColumn(name = "ssid")
	private SuperStockiestMaster superStockiestMaster;

	@ManyToOne
	@JoinColumn(name = "sid")
	private StockiestMaster StockiestMaster;

	@Column(name = "createdBy")
	private String createdBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	@Column(name = "createdDate")
	private Date createdDate;

	@Column(name = "updatedBy")
	private String updatedBy;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	@Column(name = "updatedDate")
	private Date updatedDate;

	@Column(name = "approved")
	private String approved;

	@Column(name = "approvedby")
	private String approvedby;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	@Column(name = "appdate")
	private Date appdate;

	@Column(name = "kycstatus")
	private String kycStatus;

	@Column(name = "remarks")
	private String remarks;

	@Column(name = "status")
	private String status;

	@Column(name = "two_fa_code")
	private String twoFacode;

	@Column(name = "two_fa_expire_time", nullable = false)
	private long twoFaExpireTime;

	@Column(name = "gstnumber")
	private String gstNumber;

	@Column(name = "aadharnumber")
	private String aadharNumber;

	@Column(name = "yblagentid", nullable = false)
	private String yblagentid;

	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "epagentid", nullable = false)
	private String epagentid;

	@JsonProperty("aBalance")
	@Column(name = "abalance", nullable = false)
	private double aBalance;

	@Column(name = "wbalance", nullable = false)
	@JsonProperty("wBalance")
	private double wBalance;

	@Column(name = "latittude", nullable = false)
	private BigDecimal latittude;

	@Column(name = "longitude", nullable = false)
	private BigDecimal longitude;

	@Column(name = "paymenttype", nullable = false)
	private String paymenttype;

	@Column(name = "paymentid", nullable = false)
	private String paymentid;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	@Column(name = "paydate", nullable = false)
	private Date paydate;

	@Column(name = "payamount", nullable = false)
	private double payamount;

	@Column(name = "paymobile", nullable = false)
	private String paymobile;

	@Column(name = "payatmid", nullable = false)
	private String payatmid;

	@Column(name = "depbank", nullable = false)
	private String depbank;

	@Column(name = "terminalid", nullable = false)
	private String terminalid;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss a")
	@Column(name = "fcleardate", nullable = false)
	private Date fcleardate;

	@Column(name = "uniq_id")
	private String uniqId;

	@Column(name = "arzoo_ta_id")
	private String arzooTaId;

	@Column(name = "arzoo_ta_status")
	private String arzooTaStatus;

	@Column(name = "arzoo_ta_partner_id")
	private String arzooTaPartnerId;

	@Column(name = "pkgtype", nullable = false)
	private String pkgtype;

	@Column(name = "tpin", nullable = false)
	private int tpin;

	@Column(name = "irctcid", nullable = false)
	private String irctcid;

	@Column(name = "dmt_status_show", nullable = false)
	private boolean dmtStatusShow;

	@Column(name = "bbpsid", nullable = false)
	private String bbpsid;


	
	
}
