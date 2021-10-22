package com.data.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.admin.bean.UserDetails;
import com.data.admin.service.AdminUserService;

@RestController
public class AdminUserController {
   @Autowired
	private AdminUserService adminUserService;
	
	@GetMapping("/userdetails")
	public List<UserDetails> getDetails(){
		return  adminUserService.getUserDetails();
	}
}
