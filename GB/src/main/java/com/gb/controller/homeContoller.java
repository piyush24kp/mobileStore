package com.gb.controller;

import java.util.List;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.model.OrderDetail;
import com.gb.service.impl.OrderServiceImpl;
import com.gb.service.impl.userServiceImpl;
import com.gb.vo.AllSuppliersDetailVo;
import com.gb.vo.OrderDetailVo;
import com.gb.vo.SupplierDetailVo;
import com.gb.vo.UserDetailsVo;

@Controller
public class homeContoller {

	@Autowired 
	OrderServiceImpl orderServiceImpl;
	
	@RequestMapping(value = "/getOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<OrderDetailVo>> getOrders(){
		
		List<OrderDetailVo> orderDetail = null; 
		
		orderDetail = orderServiceImpl.getOrders(); 
				
		if (orderDetail.size()>0)
    	{
    		return new ResponseEntity<List<OrderDetailVo>>(orderDetail, HttpStatus.OK);	
		}
    	
    	return new ResponseEntity<List<OrderDetailVo>>(orderDetail, HttpStatus.NO_CONTENT);
		
	}
	
	
	@RequestMapping(value = "/setOrders", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	
    public ResponseEntity<OrderDetailVo> setOrders(@RequestBody OrderDetailVo orderDetail) { 
 
		OrderDetailVo orderDetailvo = orderServiceImpl.createOrder(orderDetail);
		if(orderDetailvo != null){
			return new ResponseEntity<OrderDetailVo>(orderDetail, HttpStatus.OK); 
		}
		return new ResponseEntity<OrderDetailVo>(orderDetail, HttpStatus.BAD_REQUEST); 
    }
	
	
	@RequestMapping(value = "/updateOrder", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	
    public ResponseEntity<OrderDetailVo> updateOrder(@RequestBody OrderDetailVo orderDetail) { 

		boolean flag = orderServiceImpl.updateOrder(orderDetail);
		if(flag){
			return new ResponseEntity<OrderDetailVo>(orderDetail, HttpStatus.OK); 
		}
		return new ResponseEntity<OrderDetailVo>(orderDetail, HttpStatus.BAD_REQUEST); 
    }
	
	
	@RequestMapping(value = "/createSupplier", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
	
    public ResponseEntity<SupplierDetailVo> createSupplier(@RequestBody SupplierDetailVo supplierDetailVo) { 

		SupplierDetailVo vo = orderServiceImpl.createSupplier(supplierDetailVo);
		if(vo != null){
			return new ResponseEntity<SupplierDetailVo>(vo, HttpStatus.OK); 
		}
		return new ResponseEntity<SupplierDetailVo>(vo, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/getSupplier", method = RequestMethod.GET, produces = "application/json")
	
    public ResponseEntity<List<SupplierDetailVo>> getSupplier() { 

		List<SupplierDetailVo> vo = orderServiceImpl.getSupplier();
		if(vo != null){
			return new ResponseEntity<List<SupplierDetailVo>>(vo, HttpStatus.OK); 
		}
		return new ResponseEntity<List<SupplierDetailVo>>(vo, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/getSupplierId", method = RequestMethod.GET, produces = "application/json")
	
    public ResponseEntity<List<AllSuppliersDetailVo>> getSupplierId() { 

		List<AllSuppliersDetailVo> vo = orderServiceImpl.getSupplierId();
		if(vo.size()>0){
			return new ResponseEntity<List<AllSuppliersDetailVo>>(vo, HttpStatus.OK); 
		}
		return new ResponseEntity<List<AllSuppliersDetailVo>>(vo, HttpStatus.BAD_REQUEST); 
    }
	
	/*@RequestMapping(value = "/getFamily", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<UserDetailsVo>> getFamily(@QueryParam("uid") Integer uid ){
		
		List<UserDetailsVo> userDetails = null; 
		
		userDetails = userServiceImpl.getFamily(uid); 
				
		if (userDetails.size()>0)
    	{
    		return new ResponseEntity<List<UserDetailsVo>>(userDetails, HttpStatus.OK);	
		}
    	
    	return new ResponseEntity<List<UserDetailsVo>>(userDetails, HttpStatus.NO_CONTENT);
		
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<UserDetailsVo>> getUser(@QueryParam("name") String name ){
		
		List<UserDetailsVo> userDetails = null; 
		
		userDetails = userServiceImpl.getUser(name); 
				
		if (userDetails.size()>0)
    	{
    		return new ResponseEntity<List<UserDetailsVo>>(userDetails, HttpStatus.OK);	
		}
    	
    	return new ResponseEntity<List<UserDetailsVo>>(userDetails, HttpStatus.NO_CONTENT);
		
	}*/
}
