package com.gb.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SellDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="orderId")
	private Long orderId;
	
	@Column(name="invoiceNo")
	private String invoiceNo;
	
	@Column(name="customerName")
	private String customerName;
	
	@Column(name="contantNo")
	private Long contantNo;
	
	@Column(name="imeiNo")
	private Long imeiNo;
	
	@Column(name="brand")
	private String brand;
	
	@Column(name="model")
	private String model;
	
	@Column(name="saleType")
	private String saleType;
	
	@Column(name="address")
	private String address;
	
	@Column(name="sellDate")
	private Date sellDate;
	
	@Column(name="amount")
	private Long amount;
	
	@Column(name="vendor")
	private String vendor;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getContantNo() {
		return contantNo;
	}
	public void setContantNo(Long contantNo) {
		this.contantNo = contantNo;
	}
	public Long getImeiNo() {
		return imeiNo;
	}
	public void setImeiNo(Long imeiNo) {
		this.imeiNo = imeiNo;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getSellDate() {
		return sellDate;
	}
	public void setSellDate(Date sellDate) {
		this.sellDate = sellDate;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

}
