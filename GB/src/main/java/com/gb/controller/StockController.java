package com.gb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.gb.model.ModelDetail;
import com.gb.model.OrderDetail;
import com.gb.model.SupplierDetail;
import com.gb.service.impl.OrderServiceImpl;
import com.gb.vo.AllModelsVo;
import com.gb.vo.AllSuppliersDetailVo;
import com.gb.vo.BrandDetailVo;
import com.gb.vo.ModelDetailVo;
import com.gb.vo.OrderDetailVo;
import com.gb.vo.ResponceData;
import com.gb.vo.SupplierDetailVo;
import com.gb.vo.UserDetailsVo;
import com.mysql.fabric.xmlrpc.base.Array;

@Controller
@RequestMapping(value="/stock")
public class StockController {

	@Autowired 
	OrderServiceImpl orderServiceImpl;
	
	@RequestMapping(value = "/getOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <ResponceData> getOrders(){
		ResponceData responce = new ResponceData();
		List<OrderDetailVo> orderDetail  = orderServiceImpl.getOrders(); 
		if (orderDetail.size()>0)
    	{	
			responce.setDatabean(orderDetail);
			responce.setMessage("Success");
    		return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);	
		}
		responce.setMessage("error");
		responce.setError("NO_CONTENT_FOUND");
    	return new ResponseEntity<ResponceData>(responce, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/setOrders", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> setOrders(@RequestBody OrderDetail orderDetail) { 
		OrderDetailVo orderDetailvo = orderServiceImpl.createOrder(orderDetail);
		ResponceData responce = new ResponceData();
		if(orderDetailvo != null){
			responce.setDatabean(orderDetailvo);
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>( responce, HttpStatus.OK); 
		}
		return new ResponseEntity<ResponceData>( responce, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/updateOrder", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> updateOrder(@RequestBody OrderDetail orderDetail) { 
		boolean flag = orderServiceImpl.updateOrder(orderDetail);
		ResponceData responce = new ResponceData();
		if(flag){
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		responce.setMessage("error");
		responce.setError("BAD_REQUEST");
		return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/deleteOrder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponceData> deleteOrder(@QueryParam("id")Long id){
		ResponceData responce = new ResponceData();
		if (orderServiceImpl.deleteOrder(id))
    	{
			responce.setMessage("Deleted Succesfully.");
    		return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);	
		}
		responce.setMessage("error");
    	return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/createSupplier", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> createSupplier(@RequestBody SupplierDetailVo supplierDetailVo) { 
		SupplierDetailVo vo = orderServiceImpl.createSupplier(supplierDetailVo);
		ResponceData responce = new ResponceData();
		if(vo != null){
			responce.setDatabean(vo);
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		responce.setMessage("error");
		return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/getSupplier", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResponceData> getSupplier() { 
		List<SupplierDetailVo> vo = orderServiceImpl.getSupplier();
		ResponceData responce = new ResponceData();
		if(vo != null){
			responce.setDatabean(vo);
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		responce.setMessage("error");
		return new ResponseEntity<ResponceData>(responce, HttpStatus.NO_CONTENT); 
    }
	
	@RequestMapping(value = "/getSupplierId", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResponceData> getSupplierId() { 
		List<AllSuppliersDetailVo> vo = orderServiceImpl.getSupplierId();
		ResponceData responce = new ResponceData();
		if(vo.size()>0){
			responce.setDatabean(vo);
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		responce.setMessage("error");
		return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/updateSupplier", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> updateSupplier(@RequestBody SupplierDetail supplierDetail) { 
		boolean flag = orderServiceImpl.updateSupplier(supplierDetail);
		ResponceData responce = new ResponceData();
		if(flag){
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		responce.setMessage("error");
		return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/deleteSupplier", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <ResponceData> deleteSupplier(@QueryParam("id")Long id){
		Map<String, String> orderDetail = new HashMap(); 
		ResponceData responce = new ResponceData();
		if (orderServiceImpl.deleteSupplier(id)){
			responce.setMessage("Deleted Succesfully.");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);	
		}
		responce.setMessage("error");
    	return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/getBrands", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResponceData> getBrands() { 
		List<BrandDetailVo> vo = orderServiceImpl.getBrands();
		ResponceData responce = new ResponceData();
		if(vo.size()>0){
			responce.setDatabean(vo);
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/createBrands", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> createBrands(@RequestBody BrandDetailVo brandDetailVo) { 
		BrandDetailVo vo = orderServiceImpl.createBrands(brandDetailVo);
		ResponceData responce = new ResponceData();
		if(vo != null){
			responce.setDatabean(vo);
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/updateBrands", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> updateBrands(@RequestBody BrandDetailVo brandDetailVo) { 
		boolean flag = orderServiceImpl.updateBrands(brandDetailVo);
		ResponceData responce = new ResponceData();
		if(flag){
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		responce.setMessage("error");
		return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/deleteBrand", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponceData> deleteBrand(@QueryParam("id")Long id){
		ResponceData responce = new ResponceData();
		if (orderServiceImpl.deleteBrands(id))
    	{
			responce.setMessage("Deleted Succesfully.");
    		return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);	
		}
		responce.setMessage("error");
    	return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/getModels", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResponceData> getModels() {
		List<ModelDetailVo> vo = orderServiceImpl.getModels();
		ResponceData responce = new ResponceData();
		if(vo.size()>0){
			responce.setDatabean(vo);
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/getModelById", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResponceData> getModelByBrandId(@QueryParam("id") Long id) {
		List<AllModelsVo> vo = orderServiceImpl.getModelByBrandId(id);
		ResponceData responce = new ResponceData();
		if(vo.size()>0){
			responce.setDatabean(vo);
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/setModel", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> setModel(@RequestBody ModelDetail modelDetail) {
		ModelDetailVo vo = orderServiceImpl.setModel(modelDetail);
		ResponceData responce = new ResponceData();
		if(vo != null){
			responce.setDatabean(vo);
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/updateModel", method = RequestMethod.POST, produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponceData> updateModel(@RequestBody ModelDetail ModelDetail) { 
		boolean flag = orderServiceImpl.updateModel(ModelDetail);
		ResponceData responce = new ResponceData();
		if(flag){
			responce.setMessage("Success");
			return new ResponseEntity<ResponceData>(responce, HttpStatus.OK); 
		}
		responce.setMessage("error");
		return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST); 
    }
	
	@RequestMapping(value = "/deleteModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponceData> deleteModel(@QueryParam("id")Long id){
		ResponceData responce = new ResponceData();
		if (orderServiceImpl.deleteModel(id))
    	{
			responce.setMessage("Deleted Succesfully.");
    		return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);	
		}
		responce.setMessage("error");
    	return new ResponseEntity<ResponceData>(responce, HttpStatus.BAD_REQUEST);
	}
}
