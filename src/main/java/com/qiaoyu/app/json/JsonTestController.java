package com.qiaoyu.app.json;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qiaoyu.app.dao.model.User;
import com.qiaoyu.app.service.user.UserService;

@RestController
public class JsonTestController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUsers")
	public List<User> getUsers()
	{
		return userService.getAllUsers();
	}

}
