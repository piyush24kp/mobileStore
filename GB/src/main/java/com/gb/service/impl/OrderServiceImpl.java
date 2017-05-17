package com.gb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.model.BrandDetail;
import com.gb.model.ModelDetail;
import com.gb.model.OrderDetail;
import com.gb.model.SupplierDetail;
import com.gb.repository.OrderRepository;
import com.gb.service.OrderService;
import com.gb.vo.AllModelsVo;
import com.gb.vo.AllSuppliersDetailVo;
import com.gb.vo.BrandDetailVo;
import com.gb.vo.ModelDetailVo;
import com.gb.vo.OrderDetailVo;
import com.gb.vo.SupplierDetailVo;


@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<OrderDetailVo> getOrders() {
		List<OrderDetailVo> orderDetailVo = new ArrayList<OrderDetailVo>();
		List<OrderDetail> orderDetail = orderRepository.getOrders();
		for(OrderDetail order : orderDetail){
			OrderDetailVo detailVo = new OrderDetailVo();
			detailVo = parseOrder(order,detailVo);
			orderDetailVo.add(detailVo);
		}
		return orderDetailVo;
	}

	@Override
	public OrderDetailVo createOrder(OrderDetail odDetailVo) {
		Long orderId = odDetailVo.getOrderId();
		OrderDetailVo detailVo =  new OrderDetailVo();
		if(orderId == null){
			Long millis = System.currentTimeMillis() / 1000L;
			odDetailVo.setOrderId(millis);
			orderId = millis;
		}
		List<OrderDetail> odList = orderRepository.getOrderByBrandAndModel(odDetailVo.getBrand(),odDetailVo.getModel());
		if(odList.size()>0){
			OrderDetail  detail = new OrderDetail();
			detail = odList.get(0);
			detail.setQuantity(detail.getQuantity()+1);
			if(updateOrder(detail)){
				OrderDetail order = orderRepository.getOrders(detail.getOrderId());
				detailVo = parseOrder(order,detailVo);
			}
		}else{
			odDetailVo.setQuantity(1);
			Integer no = orderRepository.createOrder(odDetailVo);
			if(no.equals(1)){
				OrderDetail order = 	orderRepository.getOrders(orderId);
				detailVo = parseOrder(order,detailVo);
			}
		}
		return detailVo;
		
	}

	@Override
	public boolean updateOrder(OrderDetail odDetailVo) {
		Integer no = orderRepository.updateOrder(odDetailVo);
		if(no.equals(1)){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteOrder(Long orderId) {
		Integer no = orderRepository.deleteOrder(orderId);
		if(no.equals(1)){
			return true;
		}
		return false;
	}
	
	@Override
	public SupplierDetailVo createSupplier(SupplierDetailVo odDetailVo) {
		SupplierDetailVo detailVo = new SupplierDetailVo();
		Integer no = orderRepository.createSupplier(odDetailVo);
		if(no.equals(1)){
			SupplierDetail sd = orderRepository.getSupplierById();
			  detailVo.setSupplierId(sd.getSupplierId());
			  detailVo.setAddress(sd.getAddress());
			  detailVo.setAmountDue(sd.getAmountDue());
			  detailVo.setAmountpaid(sd.getAmountpaid());
			  detailVo.setSupplierName(sd.getSupplierName());
			  detailVo.setContactNo(sd.getContactNo());
			}
		return detailVo;
	}
	
	@Override
	public List<SupplierDetailVo> getSupplier() {
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
	public boolean updateSupplier(SupplierDetail odDetailVo) {
		Integer no = orderRepository.updateSupplier(odDetailVo);
		if(no.equals(1)){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean deleteSupplier(Long supplierId) {
		Integer no = orderRepository.deleteSupplier(supplierId);
		if(no.equals(1)){
			return true;
		}
		return false;
	}
	
	@Override
	public List<AllSuppliersDetailVo> getSupplierId() {
		List<AllSuppliersDetailVo> supplierDetailVos = new ArrayList<AllSuppliersDetailVo>();
		List<SupplierDetail> supplierDetails = orderRepository.getSupplierIdAndName();
		for(SupplierDetail suDetail : supplierDetails){
			AllSuppliersDetailVo detailVo = new AllSuppliersDetailVo();
			detailVo.setSupplierId(suDetail.getSupplierId());
			detailVo.setSupplierName(suDetail.getSupplierName());
			supplierDetailVos.add(detailVo);
		}
		return supplierDetailVos;
	}
	
	@Override
	public List<BrandDetailVo> getBrands() {
		List<BrandDetailVo> brandDetailVos = new ArrayList<BrandDetailVo>();
		List<BrandDetail> brandDetails = orderRepository.getBrands();
		for(BrandDetail order : brandDetails){
			BrandDetailVo detailVo = new BrandDetailVo();
			detailVo.setBrandId(order.getBrandId());
			detailVo.setBrandName(order.getBrandName());
			brandDetailVos.add(detailVo);
		}
		return brandDetailVos;
	}
	@Override
	public BrandDetailVo createBrands(BrandDetailVo brandDetailVo) {
		BrandDetailVo detailVo = new BrandDetailVo();
		Integer no = orderRepository.createBrands(brandDetailVo);
		if(no.equals(1)){
		  BrandDetail order = orderRepository.getUpdateBrand(brandDetailVo);
			detailVo.setBrandId(order.getBrandId());
			detailVo.setBrandName(order.getBrandName());
		}
		return detailVo;
	}

	public List<AllModelsVo> getModelByBrandId(Long brandId) {
		List<AllModelsVo> model = new ArrayList<>();
		List<ModelDetail> detailsList = orderRepository.getModelByBrandId(brandId);
		for(ModelDetail bd : detailsList){
			AllModelsVo vo = new AllModelsVo();
			vo.setModelId(bd.getModelId());
			vo.setModelName(bd.getModelName());
			model.add(vo);
		}
		return model;
	}

	public OrderDetailVo parseOrder( OrderDetail order , OrderDetailVo detailVo){
		detailVo.setOrderId(order.getOrderId());
		detailVo.setAmount(order.getAmount());
		detailVo.setCategory(order.getCategory());
		detailVo.setOrderName(order.getOrderName());
		detailVo.setPurchasePrice(order.getPurchasePrice());
		detailVo.setQuantity(order.getQuantity());
		detailVo.setSellPrice(order.getSellPrice());
		detailVo.setOrderDate(order.getOrderDate());
		
		SupplierDetail sd= orderRepository.getSupplierId(order.getSuppliedBy());
		AllSuppliersDetailVo asd = new AllSuppliersDetailVo();
		asd.setSupplierId(sd.getSupplierId());
		asd.setSupplierName(sd.getSupplierName());
		detailVo.setSuppliedBy(asd);
		
		BrandDetail bd = orderRepository.getBrandById(Long.parseLong(order.getBrand()));
		BrandDetailVo bdv = new BrandDetailVo();
		bdv.setBrandId(bd.getBrandId());
		bdv.setBrandName(bd.getBrandName());
		detailVo.setBrand(bdv);
		
		ModelDetail md = orderRepository.getModelById(Long.parseLong(order.getModel()));
		AllModelsVo amv = new AllModelsVo();
		amv.setModelId(md.getModelId());
		amv.setModelName(md.getModelName());
		detailVo.setModel(amv);
		return detailVo;
	}

	public ModelDetailVo setModel(ModelDetail modelDetail) {
		ModelDetailVo detailVo = new ModelDetailVo();
		Integer no = orderRepository.setModel(modelDetail);
		if(no.equals(1)){
			ModelDetail order = orderRepository.getUpdateModel(modelDetail);
			detailVo.setModelId(order.getModelId());
			detailVo.setModelName(order.getModelName());
			detailVo.setPrice(order.getPrice());
			detailVo.setStorage(order.getStorage());
			detailVo.setBrandId(order.getBrandId());
		}
		return detailVo;
	}
}
