package com.gb.service;

import java.util.List;

import com.gb.vo.AllSuppliersDetailVo;
import com.gb.vo.OrderDetailVo;
import com.gb.vo.SupplierDetailVo;

public interface OrderService {

	public List<OrderDetailVo> getOrders();
	
	public OrderDetailVo createOrder(OrderDetailVo odDetailVo);
	
	public boolean updateOrder(OrderDetailVo odDetailVo);
	
	public SupplierDetailVo createSupplier(SupplierDetailVo detailVo);
	
	public List<SupplierDetailVo> getSupplier();
	
	public List<AllSuppliersDetailVo> getSupplierId();
	
	
	
}
