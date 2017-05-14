package com.gb.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gb.model.userDetails;
import com.gb.vo.UserDetailsVo;

@Repository
public class UserRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<userDetails> getFamily(Integer uid) {
		
		List<userDetails> userDetails = null;
		
		String baseQuery = "select FatherId,MotherId FROM userDetails where uid = "+uid;
		
		userDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(userDetails.class));
		
		return userDetails;
	}
	
	public userDetails getFatherName(Integer uid){
		
		userDetails userDetails = null;
		
		String baseQuery = "select * from userDetails where UID = (select FatherId FROM userDetails where uid = "+ uid +")";
		
		return userDetails;
		
	}
	
	public userDetails getMotherName(Integer uid){
		
		userDetails userDetails = null;
		
		String baseQuery = "select * from userDetails where UID = (select MotherId FROM userDetails where uid = "+ uid +")";
		
		return userDetails;
		
	}
	
	public List<userDetails> getParents(Integer uid){
		
		List<userDetails> userDetails = null;
		
		String baseQuery = "select * from userDetails where UID = (select FatherId FROM userDetails where uid = "+ uid +") OR UID = (select MotherId FROM userDetails where uid = "+ uid +")";
		
		userDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(userDetails.class));
		
		return userDetails;
		
	}
	
	public List<userDetails> getPersonalDetail(Integer uid){
		
		List<userDetails> userDetails = null;
		
		String baseQuery = "select * from userDetails where UID = "+ uid;
		
		userDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(userDetails.class));
		
		return userDetails;
		
	}
	
	public List<userDetails> getChildrens(Integer uid){
		
		List<userDetails> userDetails = null;
		
		String baseQuery = "select DISTINCT * from userDetails where FatherId = "+ uid +" or MotherId = "+uid;
		
		userDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(userDetails.class));
		
		return userDetails;
		
	}

	public List<UserDetailsVo> getUser(String name) {
		List<UserDetailsVo> userDetails = null;

		String baseQuery = "select * from userDetails where Firstname LIKE '"+name+"%'";
		
		userDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(userDetails.class));
		
		return userDetails;
	}

	public List<userDetails> getSibling(Integer uid) {
		
		List<userDetails> userDetails = null;
		
		String baseQuery = "select * from userDetails where (FatherId = (select FatherId from userDetails where UID = "+ uid +") or MotherId = (select MotherId from userDetails where UID = "+ uid +")) and not UID ="+uid;
		
		userDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(userDetails.class));
		
		return userDetails;
	}

	public List<userDetails> getSpouse(Integer uid) {
		List<userDetails> userDetails = null;
		
		String baseQuery = "select * from userDetails where spouseId = "+ uid ;
		
		userDetails = jdbcTemplate.query(baseQuery, new BeanPropertyRowMapper(userDetails.class));
		
		return userDetails;
	}

}
