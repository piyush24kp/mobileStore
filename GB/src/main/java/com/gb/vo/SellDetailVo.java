package com.gb.vo;

import java.sql.Date;

public class SellDetailVo {
	private Long orderId;
	private Long invoiceNo;
	private String customerName;
	private Long contantNo;
	private Long imeiNo;
	private BrandDetailVo brand;
	private AllModelsVo model;
	private String saleType;
	private String address;
	private Date sellDate;
	private Long amount;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(Long invoiceNo) {
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
	public BrandDetailVo getBrand() {
		return brand;
	}
	public void setBrand(BrandDetailVo brand) {
		this.brand = brand;
	}
	public AllModelsVo getModel() {
		return model;
	}
	public void setModel(AllModelsVo model) {
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
	
	

}
