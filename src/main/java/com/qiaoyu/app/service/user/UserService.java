package com.qiaoyu.app.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiaoyu.app.dao.model.User;
import com.qiaoyu.app.dao.model.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers()
	{
		return (List<User>) userRepository.findAll();
	}
}
