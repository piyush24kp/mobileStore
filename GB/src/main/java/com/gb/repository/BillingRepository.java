package com.gb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gb.model.OrderDetail;
import com.gb.model.SellDetail;
import com.gb.vo.SupplierDetailVo;

@Repository
public class BillingRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<SellDetail> getSellOrders() {
		List<SellDetail> sellDetails = null;
		String baseQuery = "select * FROM sellDetail";
		sellDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(SellDetail.class));
		return sellDetails;
	}
	
	public Integer setSellOrder(SellDetail sellDetail) {
		String baseQuery = "INSERT INTO sellDetail(invoiceNo,customerName,contantNo,imeiNo,brand,model,saleType,address,sellDate,amount) VALUES(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {sellDetail.getInvoiceNo(),sellDetail.getCustomerName(),sellDetail.getContantNo(),sellDetail.getImeiNo(),sellDetail.getBrand(),sellDetail.getModel(),sellDetail.getSaleType(),sellDetail.getAddress(),sellDetail.getSellDate(),sellDetail.getAmount()};
		return jdbcTemplate.update(baseQuery, params);
	}
	
	public Integer updateSellOrder(SellDetail sd) {
		String baseQuery = "UPDATE sellDetail set orderId = ?, invoiceNo = ?,customerName = ?,contantNo = ?,imeiNo = ?,brand = ?,model = ?,saleType = ?,address = ?,sellDate = ?,amount = ? WHERE orderId = ?";
		Object[] params = new Object[] { sd.getOrderId(),sd.getInvoiceNo(),sd.getCustomerName(),sd.getContantNo(),sd.getImeiNo(),sd.getBrand(),sd.getModel(),sd.getSaleType(),sd.getAddress(),sd.getSellDate(),sd.getAmount(),sd.getOrderId() };
		return jdbcTemplate.update(baseQuery, params);
	}
	
	public Integer deleteSellOrder(Long sellOrderId) {
		String baseQuery = "DELETE FROM sellDetail where orderId = ?";
		Object[] params = new Object[] {sellOrderId };
		return jdbcTemplate.update(baseQuery, params);
	}
}
