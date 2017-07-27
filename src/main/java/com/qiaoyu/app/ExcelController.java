package com.qiaoyu.app;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qiaoyu.app.service.order.OrderService;
import com.qiaoyu.app.util.ExcelUtil;

@Controller
public class ExcelController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/doOrderStatistics")
	public void doOrderStatistics(@RequestParam String deliveryDateFrom, @RequestParam String deliveryDateTo,@RequestParam String deliveryProvider, HttpServletResponse response)
	{
		String outPutFileName = deliveryProvider + " "+deliveryDateFrom + "-"+deliveryDateTo + " order statistics.xls";
        OutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
        response.reset();
        
        response.setHeader("content-disposition","attachment;filename="+outPutFileName);
        response.setContentType("APPLICATION/msexcel");       
        
        List<String> header = new ArrayList<String>();
        header.add("Shipping Country");
        header.add("Total Parcel");
        header.add("Total Quantity");
        header.add("Item And Quantity");
        
        List<Object> data=new ArrayList<Object>();
        
        Map<String, Map<String, Object>> map = orderService.getOrderStatistics(deliveryDateFrom, deliveryDateTo, deliveryProvider);
        
        for(String key : map.keySet())
        {
        	List<Object> cellData = new ArrayList<Object>();
        	cellData.add(key);
        	
        	Map<String, Object> detailMap = map.get(key);
        	cellData.add(detailMap.get("totalParcel"));
        	cellData.add(detailMap.get("totalQuantity"));
        	
        	detailMap.remove("totalParcel");
        	detailMap.remove("totalQuantity");
        	
        	StringBuilder sb = new StringBuilder();
        	for(String itemKey : detailMap.keySet())
        	{
        		sb.append(itemKey).append("  ").append(detailMap.get(itemKey)).append("   ");
        	}
        	
        	cellData.add(sb);
        	
        	data.add(cellData);
        }
        
        try {
			ExcelUtil.writeExcel(out, header, data);
			out.close();
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
