package com.gb.repository;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gb.model.OrderDetail;
import com.gb.model.SupplierDetail;
import com.gb.model.userDetails;
import com.gb.vo.OrderDetailVo;
import com.gb.vo.SupplierDetailVo;

@Repository
public class OrderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<OrderDetail> getOrders() {
		
		List<OrderDetail> orderDetail = null;
		
		String baseQuery = "select * FROM orderDetails";
		
		orderDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(OrderDetail.class));
		
		return orderDetail;
	}
	
	public OrderDetail getOrders(Long orderID) {
			
			List<OrderDetail> orderDetail = null;
			
			String baseQuery = "select * FROM orderDetails WHERE orderId =" + orderID;
			
			orderDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(OrderDetail.class));
			
			return orderDetail.get(0);
		}

	public Integer createOrder(OrderDetailVo odDetailVo) {
		
		String baseQuery = "INSERT INTO orderDetails(orderId, orderName,quantity,brand,category,purchasePrice,sellPrice,suppliedBy) VALUES(?,?,?,?,?,?,?,?)";
		
		Object[] params = new Object[] { odDetailVo.getOrderId(),odDetailVo.getOrderName(),odDetailVo.getQuantity(),odDetailVo.getBrand(),odDetailVo.getCategory(),odDetailVo.getPurchasePrice(),odDetailVo.getSellPrice(),odDetailVo.getSuppliedBy() };
		
		//int[] types = new int[] { Types.INTEGER, Types.VARCHAR,Types.INTEGER, Types.VARCHAR, Types.VARCHAR,Types.INTEGER,Types.INTEGER ,Types.VARCHAR};
		
		return jdbcTemplate.update(baseQuery,params);
		
	}
	
	public Integer updateOrder(OrderDetailVo odDetailVo) {
		
		String baseQuery = "UPDATE orderDetails set orderId = ?, orderName = ?,quantity = ?,brand = ?,category = ?,purchasePrice = ?,sellPrice = ?,suppliedBy = ? WHERE orderId = ?";
		
		Object[] params = new Object[] { odDetailVo.getOrderId(),odDetailVo.getOrderName(),odDetailVo.getQuantity(),odDetailVo.getBrand(),odDetailVo.getCategory(),odDetailVo.getPurchasePrice(),odDetailVo.getSellPrice(),odDetailVo.getSuppliedBy(),odDetailVo.getOrderId() };
		
		//int[] types = new int[] { Types.INTEGER, Types.VARCHAR,Types.INTEGER, Types.VARCHAR, Types.VARCHAR,Types.INTEGER,Types.INTEGER ,Types.VARCHAR};
		
		return jdbcTemplate.update(baseQuery,params);
		
	}
	
	public Integer createSupplier(SupplierDetailVo odDetailVo) {
		
		String baseQuery = "INSERT INTO supplier(supplierName,contactNo,address,amountDue,amountPaid) VALUES(?,?,?,?,?)";
		
		Object[] params = new Object[] { odDetailVo.getSupplierName(),odDetailVo.getContactNo(),odDetailVo.getAddress(),odDetailVo.getAmountDue(),odDetailVo.getAmountpaid()};
		
		//int[] types = new int[] { Types.INTEGER, Types.VARCHAR,Types.INTEGER, Types.VARCHAR, Types.VARCHAR,Types.INTEGER,Types.INTEGER ,Types.VARCHAR};
		
		return jdbcTemplate.update(baseQuery,params);
		
	}
	
	public SupplierDetail getSupplier(Long supplierID) {
		
		List<SupplierDetail> supplierDetails = null;
		
		String baseQuery = "select * FROM supplier WHERE orderId =" + supplierID;
		
		supplierDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(SupplierDetail.class));
		
		return supplierDetails.get(0);
	}
	
	public List<SupplierDetail> getSupplier() {
		
		List<SupplierDetail> orderDetail = null;
		
		String baseQuery = "select * FROM supplier";
		
		orderDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(SupplierDetail.class));
		
		return orderDetail;
	}
	
	public List<SupplierDetail> getSupplierId() {
		
		List<SupplierDetail> orderDetail = null;
		
		String baseQuery = "select supplierId,supplierName FROM supplier";
		
		orderDetail = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(SupplierDetail.class));
		
		return orderDetail;
	}
}
