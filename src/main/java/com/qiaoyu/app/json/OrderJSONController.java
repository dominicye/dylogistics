package com.qiaoyu.app.json;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qiaoyu.app.dao.model.Client;
import com.qiaoyu.app.dao.model.Order;
import com.qiaoyu.app.service.order.OrderService;

@RestController
public class OrderJSONController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/orderSearch")
	public List<Order> getOrders(@RequestParam String shippingCountryCode)
	{
		return orderService.getOrderListByShippingCountryCode(shippingCountryCode);
	}
	
	@RequestMapping("/orderSearchMain")
	public List<Order> getOrdersByClientNameOrProvider(@RequestParam String clientFirstName,@RequestParam String clientLastName, @RequestParam String deliveryProvider)
	{
		return orderService.getOrderListByNameOrProvider(clientFirstName, clientLastName, deliveryProvider);
	}
	
	@RequestMapping("/orderProductDetail")
	public List<Object> getOrderProducts(@RequestParam String orderId)
	{
		return orderService.getProductsByOrder(orderId);
	}
	
	@RequestMapping("/getClientInfo")
	public Client getClientByOrderId(@RequestParam String orderId)
	{
		return orderService.getClientById(orderId);
	}
	
	@RequestMapping("/bindOrderServiceProvider")
	public JsonResult bindServiceProvider(@RequestParam String orderIds, @RequestParam String spvalue)
	{
		JsonResult result = new JsonResult();
		List<Integer> ids = new ArrayList<Integer>();
		String sos = orderIds.substring(1, orderIds.length()-1);
		if(sos.contains(","))
		{
			String [] sosarray = sos.split(",");
			for(int i=0; i < sosarray.length; i++)
			{
				ids.add(Integer.valueOf(sosarray[i]));
			}
		}
		else
		{
			ids.add(Integer.valueOf(sos));
		}
		
		result.setResult(JsonResult.STATUS_SUCCESS);
		
		try{
			orderService.bindOrderServiceProvider(ids, spvalue);
		}catch(Exception e){
			result.setResult(JsonResult.STATUS_SUCCESS);
			result.setE(e);
		}
		
		return result;
	}
	
	@RequestMapping("/updateOrder")
	public JsonResult updateOrder(HttpServletRequest request)
	{
		String orderId = request.getParameter("orderId");
		String packingFee = request.getParameter("packingFee");
		String additionalFee = request.getParameter("additionalFee");
		String additionalFeeReason = request.getParameter("additionalFeeReason");
		String totalFeePayed = request.getParameter("totalFeePayed");
		String shroffFee = request.getParameter("shroffFee");
		String deliveryFee = request.getParameter("deliveryFee");
		String netWeight = request.getParameter("netWeight");
		String capacity = request.getParameter("capacity");
		String grossWeight = request.getParameter("grossWeight");
		String orderStatus = request.getParameter("orderStatus");
		String serviceProvider = request.getParameter("serviceProvider");
		String deliveryDate = request.getParameter("deliveryDate");
		String trackingNo = request.getParameter("trackingNo");
		String trackingUrl = request.getParameter("trackingUrl");
		
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStatus(JsonResult.STATUS_SUCCESS);
		
		try{
			orderService.updateOrder(Integer.valueOf(orderId), packingFee, additionalFee, additionalFeeReason,
					totalFeePayed, shroffFee, deliveryFee, netWeight, capacity, grossWeight, orderStatus, serviceProvider,
					deliveryDate, trackingNo, trackingUrl);
		}
		catch(Exception e){
			jsonResult.setStatus(JsonResult.STATUS_FAILED);
			jsonResult.setE(e);
		}
		
		return jsonResult;
	}
	
}
