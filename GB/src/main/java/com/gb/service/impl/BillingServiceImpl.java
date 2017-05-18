package com.gb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.model.BrandDetail;
import com.gb.model.ModelDetail;
import com.gb.model.OrderDetail;
import com.gb.model.SellDetail;
import com.gb.repository.BillingRepository;
import com.gb.repository.OrderRepository;
import com.gb.service.BillingService;
import com.gb.vo.AllModelsVo;
import com.gb.vo.BrandDetailVo;
import com.gb.vo.SellDetailVo;

@Service
public class BillingServiceImpl implements BillingService{

	@Autowired
	BillingRepository billingRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public void genrateBill() {
		// TODO Auto-generated method stub
		
	}

	public List<SellDetailVo> getSellOrders() {
		List<SellDetailVo> sellDetailList = new ArrayList<SellDetailVo>();
		List<SellDetail> sellDetails = billingRepository.getSellOrders();
		for(SellDetail sellDetail: sellDetails){
			SellDetailVo detailVo = new SellDetailVo();
			detailVo = parseSellDetail(detailVo, sellDetail);
			sellDetailList.add(detailVo);
		}
		return sellDetailList;
	}
	
	public SellDetailVo parseSellDetail(SellDetailVo detailVo,SellDetail detail ){
		detailVo.setOrderId(detail.getOrderId());
		detailVo.setCustomerName(detail.getCustomerName());
		detailVo.setContantNo(detail.getContantNo());
		detailVo.setImeiNo(detail.getImeiNo());
		detailVo.setInvoiceNo(detail.getInvoiceNo());
		detailVo.setAddress(detail.getAddress());
		detailVo.setAmount(detail.getAmount());
		detailVo.setSellDate(detail.getSellDate());
		detailVo.setSaleType(detail.getSaleType());
		
		BrandDetail bd = orderRepository.getBrandById(Long.parseLong(detail.getBrand()));
		BrandDetailVo bdv = new BrandDetailVo();
		bdv.setBrandId(bd.getBrandId());
		bdv.setBrandName(bd.getBrandName());
		detailVo.setBrand(bdv);
		
		ModelDetail md = orderRepository.getModelById(Long.parseLong(detail.getModel()));
		AllModelsVo amv = new AllModelsVo();
		amv.setModelId(md.getModelId());
		amv.setModelName(md.getModelName());
		detailVo.setModel(amv);
		
		return detailVo;
	}

	public SellDetailVo setSellOrder(SellDetail sellDetail) {
		
		SellDetailVo sellDetailVo =  new SellDetailVo();
		
		List<OrderDetail> odList = orderRepository.getOrderByBrandAndModel(sellDetail.getBrand(),sellDetail.getModel());
		System.out.println("Get Stock Details");
		if(odList.size()>0){
			OrderDetail detail = odList.get(0);
			if(detail.getQuantity()>0){
				String invoiceNo = null;
				if(sellDetail.getSaleType().equalsIgnoreCase("online")){
					invoiceNo = sellDetail.getVendor().substring(0, 3);
				}else{
					invoiceNo = "STR";
				}
				Long millis = System.currentTimeMillis() / 1000L;
				invoiceNo = invoiceNo + "-" + millis;
				sellDetail.setInvoiceNo(invoiceNo);
				
				if(billingRepository.setSellOrder(sellDetail).equals(1)){
					System.out.println("sell details updated");
					detail.setQuantity(detail.getQuantity()-1);
					System.out.println("Stock Quantity left "+ detail.getQuantity());
					if(orderRepository.updateOrder(detail) != null){
						System.out.println("Stock Updated");
					}
				}
			}
		}
		return sellDetailVo;
	}

	public boolean updateSellOrder(SellDetail sellDetail) {
		Integer no = billingRepository.updateSellOrder(sellDetail);
		if(no.equals(1)){
			return true;
		}
		return false;
	}
	
	public boolean deleteSellOrder(Long sellOrderId) {
		Integer no = billingRepository.deleteSellOrder(sellOrderId);
		if(no.equals(1)){
			return true;
		}
		return false;
	}
}
