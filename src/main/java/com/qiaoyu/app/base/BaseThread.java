package com.qiaoyu.app.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qiaoyu.app.service.uploadfile.CsvService;
@Component("baseThread")
public class BaseThread implements Runnable {
	
	@Autowired
	private CsvService csvService;
	
	private boolean flag = true;

	@Override
	public void run() {
		while(flag)
		{
			readOrderFilePutToDatabase();
		}

	}
	
	public void readOrderFilePutToDatabase()
	{
		System.out.println("-------Start Thread  ------------");
		
		List<String> fileNameList = csvService.getFileName();
		
		if(fileNameList.size() == 0)
		{
			System.out.println("No file can be operator waiting for new file come in");
		}
		else
		{
			System.out.println("-------System begin to read file ------------");
			Map<String, List<Map<String,String>>> map = csvService.readCSV();
			System.out.println("File record is : " + map.size());
			System.out.println("-------System read file done ------------");
			
			System.out.println("-------System will put data to database ------------");
			csvService.saveData(map, fileNameList);
			System.out.println("-------System put data to database done ------------");
		}
		
		System.out.println("-------Execute business method done  ------------");
		
		try {
			System.out.println("-------Thread Sleep  ------------");
			Thread.sleep(1000*60*60*24);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	

}
