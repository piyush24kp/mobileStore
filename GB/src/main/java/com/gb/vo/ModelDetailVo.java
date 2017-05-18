package com.gb.vo;

public class ModelDetailVo {

	private Long modelId;
	private String modelName;
	private BrandDetailVo brandId;
	private String storage;
	private String price;
	public Long getModelId() {
		return modelId;
	}
	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public BrandDetailVo getBrandId() {
		return brandId;
	}
	public void setBrandId(BrandDetailVo brandId) {
		this.brandId = brandId;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
	
}
