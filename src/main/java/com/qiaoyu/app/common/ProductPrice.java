package com.qiaoyu.app.common;

import java.util.HashMap;
import java.util.Map;

public class ProductPrice {
	
	public static Map<String, Double> productBasePriceMap = new HashMap<String, Double>();
	public static Map<String, Double> productDiscountPriceMap = new HashMap<String, Double>();
	
	static{
		productBasePriceMap.put("010100101", Double.parseDouble("309"));
		productBasePriceMap.put("010100102", Double.parseDouble("568.56"));
		productBasePriceMap.put("010100110", Double.parseDouble("2781"));
		
		productBasePriceMap.put("010200101", Double.parseDouble("309"));
		productBasePriceMap.put("010200102", Double.parseDouble("568.56"));
		productBasePriceMap.put("010200110", Double.parseDouble("2781"));
		
		productBasePriceMap.put("010300101", Double.parseDouble("309"));
		productBasePriceMap.put("010300102", Double.parseDouble("568.56"));
		productBasePriceMap.put("010300110", Double.parseDouble("2781"));
		
		productBasePriceMap.put("010400101", Double.parseDouble("309"));
		productBasePriceMap.put("010400102", Double.parseDouble("568.56"));
		productBasePriceMap.put("010400110", Double.parseDouble("2781"));
		
		productBasePriceMap.put("010500101", Double.parseDouble("309"));
		productBasePriceMap.put("010500102", Double.parseDouble("568.56"));
		productBasePriceMap.put("010500110", Double.parseDouble("2781"));
		
		productBasePriceMap.put("010600101", Double.parseDouble("309"));
		productBasePriceMap.put("010600102", Double.parseDouble("568.56"));
		productBasePriceMap.put("010600110", Double.parseDouble("2781"));
		
		productBasePriceMap.put("020000101", Double.parseDouble("329"));
		productBasePriceMap.put("020000102", Double.parseDouble("605.36"));
		productBasePriceMap.put("020000110", Double.parseDouble("2961"));
		productBasePriceMap.put("030000101", Double.parseDouble("85"));
		
		productBasePriceMap.put("040100101", Double.parseDouble("70"));
		productBasePriceMap.put("040200101", Double.parseDouble("70"));
		productBasePriceMap.put("040300101", Double.parseDouble("70"));
		productBasePriceMap.put("040400101", Double.parseDouble("70"));
		productBasePriceMap.put("040500101", Double.parseDouble("70"));
		productBasePriceMap.put("040600101", Double.parseDouble("70"));
		
	}

}
