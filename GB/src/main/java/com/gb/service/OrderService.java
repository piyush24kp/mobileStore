package com.gb.service;

import java.util.List;

import com.gb.model.ModelDetail;
import com.gb.model.OrderDetail;
import com.gb.model.SupplierDetail;
import com.gb.vo.AllModelsVo;
import com.gb.vo.AllSuppliersDetailVo;
import com.gb.vo.BrandDetailVo;
import com.gb.vo.ModelDetailVo;
import com.gb.vo.OrderDetailVo;
import com.gb.vo.SupplierDetailVo;

public interface OrderService {

	public List<OrderDetailVo> getOrders();
	public OrderDetailVo createOrder(OrderDetail odDetailVo);
	public boolean updateOrder(OrderDetail odDetailVo);
	public SupplierDetailVo createSupplier(SupplierDetailVo detailVo);
	public List<SupplierDetailVo> getSupplier();
	public List<AllSuppliersDetailVo> getSupplierId();
	public List<BrandDetailVo> getBrands();
	public BrandDetailVo createBrands(BrandDetailVo brandDetailVo);
	public boolean deleteOrder(Long orderId);
	public boolean updateSupplier(SupplierDetail odDetailVo);
	public boolean deleteSupplier(Long orderId);
	public List<AllModelsVo> getModelByBrandId(Long brandId);
	public ModelDetailVo setModel(ModelDetail modelDetail);
	
}
