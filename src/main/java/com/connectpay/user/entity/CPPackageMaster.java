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

@Entity
@Table(name = "cppackage_master")
@NamedQuery(name = "CPPackageMaster.findAll", query = "SELECT z FROM CPPackageMaster z")
public class CPPackageMaster implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "cost")
	private double cost;

	@Column(name = "packagetype",nullable=false)
	private String packageType;

	@Column(name = "minimumqty",nullable=false)
	private int minimumQty;
	
	@Column(name = "maximumQty",nullable=false)
	private int maximumQty;

	@Column(name = "buybackprice",nullable=false)
	private double buyBackPrice;

	@Column(name = "salesincentive",nullable=false)
	private double salesIncentive;

	@Column(name = "salesincentivetype",nullable=false)
	private String salesincentivetype;

	@Column(name = "cashback",nullable=false)
	private double cashBack;

	@Column(name = "product",nullable=false)
	private String product;

	@Column(name="createdBy")
	private String createdBy;
	
	@Column(name="createdDate")
	private Date createdDate;
	
	@Column(name="updatedBy")
	private String updatedBy;
	
	@Column(name="updatedDate")
	private Date updatedDate;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public int getMinimumQty() {
		return minimumQty;
	}

	public void setMinimumQty(int minimumQty) {
		this.minimumQty = minimumQty;
	}

	public int getMaximumQty() {
		return maximumQty;
	}

	public void setMaximumQty(int maximumQty) {
		this.maximumQty = maximumQty;
	}

	public double getBuyBackPrice() {
		return buyBackPrice;
	}

	public void setBuyBackPrice(double buyBackPrice) {
		this.buyBackPrice = buyBackPrice;
	}

	public double getSalesIncentive() {
		return salesIncentive;
	}

	public void setSalesIncentive(double salesIncentive) {
		this.salesIncentive = salesIncentive;
	}

	public double getCashBack() {
		return cashBack;
	}

	public void setCashBack(double cashBack) {
		this.cashBack = cashBack;
	}

	public String getSalesincentivetype() {
		return salesincentivetype;
	}

	public void setSalesincentivetype(String salesincentivetype) {
		this.salesincentivetype = salesincentivetype;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	
}
