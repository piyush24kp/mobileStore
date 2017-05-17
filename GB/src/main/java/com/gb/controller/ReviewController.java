package com.gb.controller;

import java.io.FileOutputStream;

import javax.ws.rs.QueryParam;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gb.service.impl.ReviewServiceImpl;
import com.gb.vo.ResponceData;

@Controller
public class ReviewController {

	@Autowired
	ReviewServiceImpl reviewServiceImpl;
	
    @RequestMapping(value="/myexcel", method=RequestMethod.GET)
    public ResponseEntity<ResponceData> genrateExcel(@QueryParam("type") String type){
    	
    	ResponceData responce = new  ResponceData();
    	
    	reviewServiceImpl.genrateExcel(type);

    	return new ResponseEntity<ResponceData>(responce, HttpStatus.OK);
     
    }
}