package com.gb.service;

import java.util.List;

import com.gb.vo.UserDetailsVo;

public interface UserService {

	public List<UserDetailsVo> getFamily(Integer uid);
	
	public List<UserDetailsVo> getUser(String name);
}
