package com.qiaoyu.app;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qiaoyu.app.dao.model.Order;
import com.qiaoyu.app.service.order.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/goOrderMainPage")
	public String goOrderList()
	{
		return "orderMain";
	}
	
	@RequestMapping(value="/goOrderSearch")
	public String goOrderSearch()
	{
		return "orderMainSearch";
	}

	@RequestMapping(value="/orderMantain")
	public String goOrderMantain(@RequestParam String id,ModelMap map)
	{
		map.addAttribute("orderId", id);
		Order o = orderService.getOrderById(Integer.valueOf(id));
		Date deliveryDate = o.getDeliveryDate();
		
		if(deliveryDate != null)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String strDeliveryDate = sdf.format(deliveryDate);
			map.addAttribute("strDeliveryDate", strDeliveryDate);
		}
		else
		{
			map.addAttribute("strDeliveryDate", "");
		}
		map.addAttribute("order", o);
		return "orderMantain";
	}
	
	@RequestMapping(value="/goOrderStatistics")
	public String goOrderStatistics()
	{
		return "orderStatistics";
	}
	
}