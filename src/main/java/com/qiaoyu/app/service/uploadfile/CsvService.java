package com.qiaoyu.app.service.uploadfile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.qiaoyu.app.common.OrderExcelEntry;
import com.qiaoyu.app.dao.model.Client;
import com.qiaoyu.app.dao.model.ClientRepository;
import com.qiaoyu.app.dao.model.Order;
import com.qiaoyu.app.dao.model.OrderDetail;
import com.qiaoyu.app.dao.model.OrderDetailRepository;
import com.qiaoyu.app.dao.model.OrderFileRecord;
import com.qiaoyu.app.dao.model.OrderFileRecordRepository;
import com.qiaoyu.app.dao.model.OrderRepository;
import com.qiaoyu.app.dao.model.Product;
import com.qiaoyu.app.dao.model.ProductRepository;
@Service
public class CsvService {
	
	public static final String ORIGINAL_ORDER_FILE_PATH = "D:" + File.separator + "orderFile";
	
	private static final Logger logger = LoggerFactory.getLogger(CsvService.class);
	
	@Autowired
	private OrderFileRecordRepository ofrr;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	public Map<String, List<Map<String,String>>> readCSV() {
		
		Map<String, List<Map<String,String>>> resMap = null;
		List<String> csvFileList  = getFileName();
		ArrayList<String[]> csvFileDataList = new ArrayList<String[]>();
		
		for(String csvFilePath : csvFileList)
		{
			 try {  
			        CsvReader reader = new CsvReader(csvFilePath, ',', Charset.forName("UTF-8"));  
			        //reader.readHeaders();  
			        while (reader.readRecord()) {  
			            csvFileDataList.add(reader.getValues());   
			        }  
			        reader.close();  
			    } catch (IOException e) {  
			        e.printStackTrace();  
			    } 
		}
		
		List<Map<String, String>> entryMap = buildOrderCSVEntry(csvFileDataList);  
        resMap = buildFinalEntryMap(entryMap);
		return resMap;
	} 
	
	@Transactional(value="transactionManager")
	public void saveData(Map<String, List<Map<String,String>>> entryMap, List<String> csvFileList)
	{
		for(String key : entryMap.keySet())
		{
			Client c = new Client();
			Order o = new Order();
			
			List<Product> productList = new ArrayList<Product>();
			List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			List<Map<String, String>> valueList = entryMap.get(key);
			
			for(Map<String, String> m : valueList)
			{
				c.setFirstName(m.get(OrderExcelEntry.FIRST_NAME));
				c.setLastName(m.get(OrderExcelEntry.LAST_NAME));
				c.setEmail(m.get(OrderExcelEntry.EMAIL));
				
				c.setShippingAddress(m.get(OrderExcelEntry.SHIPPING_ADDRESS));
				c.setShippingAddressee(m.get(OrderExcelEntry.SHIPPING_ADDRESSEE));
				c.setShippingAttention(m.get(OrderExcelEntry.SHIPPING_ATTENTION));
				c.setShippingAddress1(m.get(OrderExcelEntry.SHIPPING_ADDRESS1));
				c.setShippingAddress2(m.get(OrderExcelEntry.SHIPPING_ADDRESS2));
				c.setShippingCity(m.get(OrderExcelEntry.SHIPPING_CITY));
				c.setShippingStateProvince(m.get(OrderExcelEntry.SHIPPING_STATE_PROVINCE));
				c.setShippingZip(m.get(OrderExcelEntry.SHIPPING_ZIP));
				c.setShippingCountry(m.get(OrderExcelEntry.SHIPPING_COUNTRY_CODE));
				c.setShippingCountryCode(m.get(OrderExcelEntry.SHIPPING_COUNTRY));
				c.setShippingPhone(m.get(OrderExcelEntry.SHIPPING_PHONE));
				
				o.setStatus("N");
				o.setCreateDate(new Date());
				
				Product p = new Product();
				p.setItemNo(m.get(OrderExcelEntry.ITEM));
				p.setMemo(m.get(OrderExcelEntry.MEMO));
				
				productList.add(p);
				
				OrderDetail od = new OrderDetail();
				od.setOrder(o);
				od.setProduct(p);
				od.setQuantity(Integer.valueOf(m.get(OrderExcelEntry.QUANTITY_COMMITTED)));
				od.setInternalId(m.get(OrderExcelEntry.INTERNAL_ID));
				
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
		
		Date d = new Date();
		
		for(String fileName : csvFileList)
		{
			OrderFileRecord ofr = new OrderFileRecord();
			ofr.setFileName(fileName);
			ofr.setCreateDate(d);
			ofrr.save(ofr);
		}
		
	}
	
	public static List<Map<String,String>> buildOrderCSVEntry(List<String []> data)
	{
		List<Map<String, String>> entryList = new ArrayList<Map<String, String>>();
		
		for(int i = 1; i < data.size() ; i++)
		{
			String [] dl = data.get(i);
			Map<String ,String> entryMap = new HashMap<String, String>();
			entryMap.put(OrderExcelEntry.INTERNAL_ID, dl[0]);
			entryMap.put(OrderExcelEntry.FIRST_NAME, dl[4]);
			entryMap.put(OrderExcelEntry.LAST_NAME, dl[5]);
			entryMap.put(OrderExcelEntry.EMAIL, dl[6]);
			
			entryMap.put(OrderExcelEntry.SHIPPING_ADDRESS, dl[18]);
			entryMap.put(OrderExcelEntry.SHIPPING_ADDRESSEE, dl[19]);
			entryMap.put(OrderExcelEntry.SHIPPING_ATTENTION, dl[20]);
			entryMap.put(OrderExcelEntry.SHIPPING_ADDRESS1, dl[21]);
			entryMap.put(OrderExcelEntry.SHIPPING_ADDRESS2, dl[22]);
			entryMap.put(OrderExcelEntry.SHIPPING_CITY, dl[23]);
			entryMap.put(OrderExcelEntry.SHIPPING_STATE_PROVINCE, dl[24]);
			entryMap.put(OrderExcelEntry.SHIPPING_ZIP, dl[25]);
			entryMap.put(OrderExcelEntry.SHIPPING_COUNTRY, dl[26]);
			entryMap.put(OrderExcelEntry.SHIPPING_COUNTRY_CODE, dl[27]);
			entryMap.put(OrderExcelEntry.SHIPPING_PHONE, dl[29]);
			entryMap.put(OrderExcelEntry.ITEM, dl[31]);
			entryMap.put(OrderExcelEntry.MEMO, dl[33]);
			entryMap.put(OrderExcelEntry.QUANTITY_COMMITTED, dl[34]);
			
			entryList.add(entryMap);
		}
		
		return entryList;
	}
	
	public static Map<String, List<Map<String,String>>> buildFinalEntryMap(List<Map<String,String>> excelEntry)
	{
		Map<String, List<Map<String, String>>> resMap = new HashMap<String, List<Map<String, String>>>();
		List<String> internalIdList = new ArrayList<String>();
		
		for(Map<String ,String> entry : excelEntry)
		{
			String internalId = (String)entry.get(OrderExcelEntry.INTERNAL_ID);
			
			if(!internalIdList.contains(internalId))
			{
				List<Map<String, String>> lm = new ArrayList<Map<String, String>>();
				lm.add(entry);
				resMap.put(internalId, lm);
				internalIdList.add(internalId);
			}
			else
			{
				List<Map<String, String>> existingList = resMap.get(internalId);
				existingList.add(entry);
			}
			
		}
		
		return resMap;
	}
	
	public static void writeCSV() {  
	    String csvFilePath = "D://StemQ.csv";  
	    try {  
	        CsvWriter csvWriter = new CsvWriter(csvFilePath, ',', Charset.forName("UTF-8"));  
	        // head
	        String[] csvHeaders = { "no", "name", "age" };  
	        csvWriter.writeRecord(csvHeaders);  
	        // content
	        for (int i = 0; i < 20; i++) {  
	            String[] csvContent = { i + "000000", "StemQ", "1" + i };  
	            csvWriter.writeRecord(csvContent);  
	        }  
	        csvWriter.close();  
	        System.out.println("--------CSV FILE WRITE DONE--------");  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	}  
	
	public List<String> getFileName() 
	{
		List<String> fileNames = new ArrayList<String>();
		File f = new File(ORIGINAL_ORDER_FILE_PATH);
		
		if (!f.exists()) {
			logger.info(ORIGINAL_ORDER_FILE_PATH + " not exists");
			return fileNames;
		}
		 
		File fa[] = f.listFiles();
		List<OrderFileRecord> ofrList = (List<OrderFileRecord>)ofrr.findAll();
		for (int i = 0; i < fa.length; i++) 
		{
			File fs = fa[i];
			if (fs.isDirectory())
			{
				logger.info(fs.getName() + " [dictionary]");
			} 
			else
			{
				if(ofrList.size() == 0)
				{
					fileNames.add(ORIGINAL_ORDER_FILE_PATH + File.separator +fs.getName());
				}
				for(OrderFileRecord of : ofrList)
				{
					if(of.getFileName() == null || "".equals(of.getFileName()))
					{
						logger.info("file does not exist in the database");
					}
					
					if(! (ORIGINAL_ORDER_FILE_PATH + File.separator + fs.getName()).equals(of.getFileName()))
					{
						fileNames.add(ORIGINAL_ORDER_FILE_PATH + File.separator +fs.getName());
					}
				}
			}
		}
		
		return fileNames;
	}
	
}
