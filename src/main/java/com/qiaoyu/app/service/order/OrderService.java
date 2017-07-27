package com.qiaoyu.app.service.order;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiaoyu.app.dao.model.Client;
import com.qiaoyu.app.dao.model.ClientRepository;
import com.qiaoyu.app.dao.model.Order;
import com.qiaoyu.app.dao.model.OrderRepository;
import com.qiaoyu.app.dao.model.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	public List<Order> getOrderListByShippingCountryCode(String shippingCountryCode)
	{
		return orderRepository.getOrderListByShippingCountryCode(shippingCountryCode);
	}
	
	public List<Object> getProductsByOrder(String orderId)
	{
		return productRepository.getProductListByOrderId(Integer.valueOf(orderId));
	}

	public Client getClientById(String orderId) {
		return clientRepository.getClientByOrderId(Integer.valueOf(orderId));
	}
	
	public Order getOrderById(int id)
	{
		return orderRepository.findOne(id);
	}
	
	public void updateOrder(int id, String packingFee,String additionalFee, String additionalFeeReason, String totalFeePayed,
			String shroffFee, String deliveryFee, String netWeight, String capacity, String grossWeight, String orderStatus,
			String serviceProvider, String deliveryDate, String trackingNo, String trackingUrl)
	{
		Order order = getOrderById(id);
		order.setPackingFee(packingFee);
		order.setAdditionalFee(additionalFee);
		order.setAdditionalFeeReason(additionalFeeReason);
		order.setTotalFeePaied(totalFeePayed);
		order.setShroffFee(shroffFee);
		order.setDeliveryFee(deliveryFee);
		order.setNetWeight(netWeight);
		order.setCapacity(capacity);
		order.setGrossWeight(grossWeight);
		order.setStatus(orderStatus);
		order.setServiceProvider(serviceProvider);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date d = null;
		try {
			d = sdf.parse(deliveryDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		order.setDeliveryDate(d);
		order.setTrackingNo(trackingNo);
		order.setTrackingUrl(trackingUrl);
		
		orderRepository.save(order);
	}
	
	public Map<String, Map<String, Object>> getOrderStatistics(String dateFrom, String dateTo, String sp)
	{
		Map<String, Map<String, Object>> resMap = new HashMap<String, Map<String, Object>>();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date df = null;
		Date dt = null;
		try {
			df = sdf.parse(dateFrom);
			dt = sdf.parse(dateTo);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Object[]> l = clientRepository.getShippingCountryPackage(df, dt,sp);
		for(Object[] o : l)
		{
			Map<String, Object> m = new HashMap<String, Object>();
			resMap.put((String)o[0], m);
			m.put("totalParcel", (BigInteger)o[1]);
		}
		
		
		for(String key : resMap.keySet())
		{
			Map<String, Object> m = resMap.get(key);
			List<Object[]> list = clientRepository.getStatisticsInfoByShippingCountry(df, dt, sp, key);
			int totalProduct = 0;
			for(Object[] oa : list)
			{
				if(m.containsKey((String)oa[4]))
				{
					Integer quantity = (Integer)m.get((String)oa[4]);
					quantity += (Integer)oa[3];
					m.put((String)oa[4], quantity);
				}
				else
				{
					m.put((String)oa[4], (Integer)oa[3]);
				}
				totalProduct += (Integer)oa[3];
			}
			m.put("totalQuantity", totalProduct);
		}
		
		return resMap;
	}
	
	public void bindOrderServiceProvider(List<Integer> ids, String sp)
	{
		for(Integer id : ids)
		{
			Order o = orderRepository.findOne(id);
			o.setServiceProvider(sp);
			o.setStatus("P");
			orderRepository.save(o);
		}
		
	}

	public List<Order> getOrderListByNameOrProvider(String clientFirstName, String clientLastName, String deliveryProvider) {
		if((!"".equals(clientFirstName)) && (!"".equals(clientLastName)) && "".equals(deliveryProvider))
		{
			return orderRepository.getOrdersByName(clientFirstName, clientLastName);
		}
		if(!"".equals(deliveryProvider) && "".equals(clientFirstName) && "".equals(clientLastName))
		{
			return orderRepository.getOrdersByDeliveryProvider(deliveryProvider);
		}
		if((!"".equals(clientFirstName)) && (!"".equals(clientLastName)) && (!"".equals(deliveryProvider)))
		{
			return orderRepository.getOrdersByDeliveryProviderAndName(deliveryProvider, clientFirstName, clientLastName);
		}
		return null;
	}
	

}
