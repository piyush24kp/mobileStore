package com.gb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.model.userDetails;
import com.gb.repository.UserRepository;
import com.gb.service.UserService;
import com.gb.vo.UserDetailsVo;

@Service
public class userServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository ;
	
	private List<UserDetailsVo> userDetailsVo;
	
	@Override
	public List<UserDetailsVo> getFamily(Integer uid) {
		// get +1 and -1 layer structure
		
		userDetailsVo = new ArrayList<>();
		
		//user detail for responce
		UserDetailsVo user = new UserDetailsVo();
		
		//get personal details
		List<userDetails> personalDetail =  userRepository.getPersonalDetail(uid);
		user.setUid(personalDetail.get(0).getuId());
		user.setFirstName(personalDetail.get(0).getFirstName());
		user.setLastname(personalDetail.get(0).getLastName());
		user.setGender(personalDetail.get(0).getGender());
		user.setMiddleName(personalDetail.get(0).getMiddleName());
		
		//get parent details
		List<userDetails> parentDetail =  userRepository.getParents(uid);
		
		//check father or mother
		for (userDetails  details: parentDetail){
			
			UserDetailsVo fmUser = new UserDetailsVo();
			
			fmUser.setFirstName(details.getFirstName());
			fmUser.setLastname(details.getLastName());
			fmUser.setMiddleName(details.getMiddleName());
			fmUser.setGender(details.getGender());
			fmUser.setUid(details.getuId());
			
			if(details.getGender().equalsIgnoreCase("M")){
				user.setFatherName(fmUser);
			}else{
				user.setMotherName(fmUser);
			}
		}
		
		//check spouse
		List<userDetails> spouseDetail =userRepository.getSpouse(uid);
		for (userDetails  details: spouseDetail){
			
			UserDetailsVo spouse = new UserDetailsVo();
			
			spouse.setFirstName(details.getFirstName());
			spouse.setLastname(details.getLastName());
			spouse.setMiddleName(details.getMiddleName());
			spouse.setUid(details.getuId());
			
			user.setSpouseName(spouse);
		}
		
		//check children's
		List<userDetails> childDetail =userRepository.getChildrens(uid);
		
		List<UserDetailsVo> childrens = new ArrayList<>();
		
		for (userDetails  details: childDetail){
			
			UserDetailsVo child = new UserDetailsVo();
			
			child.setFirstName(details.getFirstName());
			child.setLastname(details.getLastName());
			child.setMiddleName(details.getMiddleName());
			child.setGender(details.getGender());
			child.setUid(details.getuId());
			
			childrens.add(child);
			
		}
		
		if(childrens.size()>0){
			user.setChildrens(childrens);
		}

		//check sibling's
		List<userDetails> siblings =userRepository.getSibling(uid);
		
		List<UserDetailsVo> siblingData = new ArrayList<>();
		
		for (userDetails  details: siblings){
			
			UserDetailsVo sib = new UserDetailsVo();
			
			sib.setFirstName(details.getFirstName());
			sib.setLastname(details.getLastName());
			sib.setMiddleName(details.getMiddleName());
			sib.setGender(details.getGender());
			sib.setUid(details.getuId());
			
			siblingData.add(sib);
			
		}
		
		if(siblingData.size()>0){
			user.setSiblings(siblingData);
		}
		
		userDetailsVo.add(user);
		
		return userDetailsVo;
	}

	@Override
	public List<UserDetailsVo> getUser(String name) {
		List<UserDetailsVo> userDetails =  new ArrayList<>();
		
		userDetails = userRepository.getUser(name);
		
		
		return userDetails;
	}


}
