package com.qiaoyu.app.service.uploadfile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qiaoyu.app.common.OrderExcelEntry;
import com.qiaoyu.app.dao.model.Client;
import com.qiaoyu.app.dao.model.ClientRepository;
import com.qiaoyu.app.dao.model.Order;
import com.qiaoyu.app.dao.model.OrderDetail;
import com.qiaoyu.app.dao.model.OrderDetailRepository;
import com.qiaoyu.app.dao.model.OrderRepository;
import com.qiaoyu.app.dao.model.Product;
import com.qiaoyu.app.dao.model.ProductRepository;

@Service
public class FileUploadService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	public static Map<String, List<Map<String,Object>>> buildFinalEntryMap(List<Map<String,Object>> excelEntry)
	{
		Map<String, List<Map<String, Object>>> resMap = new HashMap<String, List<Map<String, Object>>>();
		List<String> internalIdList = new ArrayList<String>();
		
		for(Map<String ,Object> entry : excelEntry)
		{
			String internalId = (String)entry.get(OrderExcelEntry.INTERNAL_ID);
			
			if(!internalIdList.contains(internalId))
			{
				List<Map<String, Object>> lm = new ArrayList<Map<String, Object>>();
				lm.add(entry);
				resMap.put(internalId, lm);
				internalIdList.add(internalId);
			}
			else
			{
				List<Map<String, Object>> existingList = resMap.get(internalId);
				existingList.add(entry);
			}
			
		}
		
		return resMap;
	}

	public static List<Map<String,Object>> buildOrderExcelEntry(List<List<Object>> data)
	{
		List<Map<String, Object>> entryList = new ArrayList<Map<String, Object>>();
		
		for(List<Object> dl : data)
		{
			Map<String ,Object> entryMap = new HashMap<String, Object>();
			entryMap.put(OrderExcelEntry.INTERNAL_ID, dl.get(1));
			entryMap.put(OrderExcelEntry.FIRST_NAME, dl.get(4));
			entryMap.put(OrderExcelEntry.LAST_NAME, dl.get(6));
			entryMap.put(OrderExcelEntry.EMAIL, dl.get(7));
			
			entryMap.put(OrderExcelEntry.SHIPPING_ADDRESS, dl.get(18));
			entryMap.put(OrderExcelEntry.SHIPPING_ADDRESSEE, dl.get(19));
			entryMap.put(OrderExcelEntry.SHIPPING_ATTENTION, dl.get(20));
			entryMap.put(OrderExcelEntry.SHIPPING_ADDRESS1, dl.get(21));
			entryMap.put(OrderExcelEntry.SHIPPING_ADDRESS2, dl.get(22));
			entryMap.put(OrderExcelEntry.SHIPPING_CITY, dl.get(23));
			entryMap.put(OrderExcelEntry.SHIPPING_STATE_PROVINCE, dl.get(24));
			entryMap.put(OrderExcelEntry.SHIPPING_ZIP, dl.get(25));
			entryMap.put(OrderExcelEntry.SHIPPING_COUNTRY, dl.get(26));
			entryMap.put(OrderExcelEntry.SHIPPING_COUNTRY_CODE, dl.get(27));
			entryMap.put(OrderExcelEntry.SHIPPING_PHONE, dl.get(29));
			entryMap.put(OrderExcelEntry.ITEM, dl.get(31));
			entryMap.put(OrderExcelEntry.MEMO, dl.get(32));
			entryMap.put(OrderExcelEntry.QUANTITY_COMMITTED, dl.get(34));
			
			entryList.add(entryMap);
		}
		
		return entryList;
	}
	
	@Transactional
	public void saveData(Map<String, List<Map<String,Object>>> entryMap)
	{
		for(String key : entryMap.keySet())
		{
			Client c = new Client();
			Order o = new Order();
			
			List<Product> productList = new ArrayList<Product>();
			List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			List<Map<String, Object>> valueList = entryMap.get(key);
			
			for(Map<String, Object> m : valueList)
			{
				c.setFirstName((String)m.get(OrderExcelEntry.FIRST_NAME));
				c.setLastName((String)m.get(OrderExcelEntry.LAST_NAME));
				c.setEmail((String)m.get(OrderExcelEntry.EMAIL));
				
				c.setShippingAddress((String)m.get(OrderExcelEntry.SHIPPING_ADDRESS));
				c.setShippingAddressee((String)m.get(OrderExcelEntry.SHIPPING_ADDRESSEE));
				c.setShippingAttention((String)m.get(OrderExcelEntry.SHIPPING_ATTENTION));
				c.setShippingAddress1((String)m.get(OrderExcelEntry.SHIPPING_ADDRESS1));
				c.setShippingAddress2((String)m.get(OrderExcelEntry.SHIPPING_ADDRESS2));
				c.setShippingCity((String)m.get(OrderExcelEntry.SHIPPING_CITY));
				c.setShippingStateProvince((String)m.get(OrderExcelEntry.SHIPPING_STATE_PROVINCE));
				c.setShippingZip((String)m.get(OrderExcelEntry.SHIPPING_ZIP));
				c.setShippingCountry((String)m.get(OrderExcelEntry.SHIPPING_COUNTRY));
				c.setShippingCountryCode((String)m.get(OrderExcelEntry.SHIPPING_COUNTRY_CODE));
				c.setShippingPhone((String)m.get(OrderExcelEntry.SHIPPING_PHONE));
				
				o.setStatus("N");
				o.setCreateDate(new Date());
				
				Product p = new Product();
				p.setItemNo((String)m.get(OrderExcelEntry.ITEM));
				p.setMemo((String)m.get(OrderExcelEntry.MEMO));
				
				productList.add(p);
				
				OrderDetail od = new OrderDetail();
				od.setOrder(o);
				od.setProduct(p);
				od.setQuantity(Integer.valueOf((String)m.get(OrderExcelEntry.QUANTITY_COMMITTED)));
				od.setInternalId((String)m.get(OrderExcelEntry.INTERNAL_ID));
				
				orderDetailList.add(od);
			}
			
			Client _client = clientRepository.save(c);
			o.setClient(_client);
			orderRepository.save(o);
			
			for(Product product : productList)
			{
				productRepository.save(product);
			}
			
			for(OrderDetail orderDetail : orderDetailList)
			{
				orderDetailRepository.save(orderDetail);
			}
			
		}
	}
	
}
