package com.gb.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.model.OrderDetail;
import com.gb.model.SupplierDetail;
import com.gb.repository.OrderRepository;
import com.gb.service.OrderService;
import com.gb.vo.AllSuppliersDetailVo;
import com.gb.vo.OrderDetailVo;
import com.gb.vo.SupplierDetailVo;
import com.mysql.fabric.xmlrpc.base.Array;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<OrderDetailVo> getOrders() {
		// TODO Auto-generated method stub
		
		List<OrderDetailVo> orderDetailVo = new ArrayList<OrderDetailVo>();
		
		List<OrderDetail> orderDetail = orderRepository.getOrders();
		
		for(OrderDetail order : orderDetail){
			
			OrderDetailVo detailVo = new OrderDetailVo();
			detailVo.setOrderId(order.getOrderId());
			detailVo.setAmount(order.getAmount());
			detailVo.setBrand(order.getBrand());
			detailVo.setCategory(order.getCategory());
			detailVo.setOrderName(order.getOrderName());
			detailVo.setPurchasePrice(order.getPurchasePrice());
			detailVo.setQuantity(order.getQuantity());
			detailVo.setSuppliedBy(order.getSuppliedBy());
			detailVo.setSellPrice(order.getSellPrice());
			
			orderDetailVo.add(detailVo);
		}
		
		return orderDetailVo;
	}

	@Override
	public OrderDetailVo createOrder(OrderDetailVo odDetailVo) {
		
		Long orderId = odDetailVo.getOrderId();
		OrderDetailVo detailVo =  new OrderDetailVo();
		
		if(orderId == null){
			Long millis = System.currentTimeMillis() / 1000L;
			odDetailVo.setOrderId(millis);
			orderId = millis;
		}
		Integer no = orderRepository.createOrder(odDetailVo);
		if(no.equals(1)){
		  OrderDetail order = 	orderRepository.getOrders(orderId);
		  
			detailVo.setOrderId(order.getOrderId());
			detailVo.setAmount(order.getAmount());
			detailVo.setBrand(order.getBrand());
			detailVo.setCategory(order.getCategory());
			detailVo.setOrderName(order.getOrderName());
			detailVo.setPurchasePrice(order.getPurchasePrice());
			detailVo.setQuantity(order.getQuantity());
			detailVo.setSuppliedBy(order.getSuppliedBy());
			detailVo.setSellPrice(order.getSellPrice());
		}
		
		return detailVo;
	}

	@Override
	public boolean updateOrder(OrderDetailVo odDetailVo) {
		Integer no = orderRepository.updateOrder(odDetailVo);
		if(no.equals(1)){
			return true;
		}
		return false;
	}
	
	@Override
	public SupplierDetailVo createSupplier(SupplierDetailVo odDetailVo) {
		
		SupplierDetailVo detailVo = new SupplierDetailVo();
		
		Integer no = orderRepository.createSupplier(odDetailVo);

		return detailVo;
	}
	
	@Override
	public List<SupplierDetailVo> getSupplier() {
		// TODO Auto-generated method stub
		
		List<SupplierDetailVo> supplierDetailVos = new ArrayList<SupplierDetailVo>();
		
		List<SupplierDetail> supplierDetails = orderRepository.getSupplier();
		
		for(SupplierDetail suDetail : supplierDetails){
			
			SupplierDetailVo detailVo = new SupplierDetailVo();
			detailVo.setSupplierId(suDetail.getSupplierId());
			detailVo.setSupplierName(suDetail.getSupplierName());
			detailVo.setContactNo(suDetail.getContactNo());
			detailVo.setAddress(suDetail.getAddress());
			detailVo.setAmountDue(suDetail.getAmountDue());
			detailVo.setAmountpaid(suDetail.getAmountpaid());
			supplierDetailVos.add(detailVo);
		}
		
		return supplierDetailVos;
	}
	
	@Override
	public List<AllSuppliersDetailVo> getSupplierId() {
		// TODO Auto-generated method stub
		
		List<AllSuppliersDetailVo> supplierDetailVos = new ArrayList<AllSuppliersDetailVo>();
		
		List<SupplierDetail> supplierDetails = orderRepository.getSupplierId();
		
		for(SupplierDetail suDetail : supplierDetails){
			
			AllSuppliersDetailVo detailVo = new AllSuppliersDetailVo();
			detailVo.setSupplierId(suDetail.getSupplierId());
			detailVo.setSupplierName(suDetail.getSupplierName());
			supplierDetailVos.add(detailVo);
		}
		
		return supplierDetailVos;
	}

}
