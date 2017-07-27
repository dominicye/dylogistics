package com.qiaoyu.app;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qiaoyu.app.dao.model.User;
import com.qiaoyu.app.service.user.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "home";
	}
	
	@RequestMapping(value = "/loginApp", method = RequestMethod.POST)
	public String login(@RequestParam String userName, @RequestParam String password, HttpServletRequest request, Model model) {
		List<User> userList = userService.getAllUsers();
		for(User user : userList)
		{
			if(userName.equals(user.getName()) && password.equals(user.getPassword()))
			{
				request.getSession().setAttribute("currentUser", user);
				return "orderMain";
			}
		}
		
		model.addAttribute("message", "invalid user name or password");
		return "home";
	}
	
}
