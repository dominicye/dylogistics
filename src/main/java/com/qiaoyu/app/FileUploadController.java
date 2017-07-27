package com.qiaoyu.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qiaoyu.app.common.AjaxResponse;
import com.qiaoyu.app.service.uploadfile.FileUploadService;
import com.qiaoyu.app.util.ExcelUtil;

@Controller
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value = "/goToFileUpload", method = RequestMethod.GET)
	public String initFileUpload()
	{
		return "fileupload";
	}
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody AjaxResponse uploadFile(HttpServletRequest req)
	{
		
        String basePath = "D:" + File.separator;
        File tmpDir = new File(basePath);

        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }

        String fileName = null;
        
        if (ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory dff = new DiskFileItemFactory();
            dff.setRepository(tmpDir);
            dff.setSizeThreshold(1024000);
            ServletFileUpload sfu = new ServletFileUpload(dff);
            sfu.setSizeMax(10000000);
            sfu.setHeaderEncoding("utf-8");

            List<FileItem> fileItems = new ArrayList<FileItem>();
            try {
                fileItems = sfu.parseRequest(req);
            } catch (FileUploadException e1) {
                logger.error("upload file failed ..."+ e1.getMessage());
            }
            String fullPath = null;
            
            for (FileItem fileItem : fileItems) {
            	 String filePath = fileItem.getName();
                 if (filePath == null || filePath.trim().length() == 0)
                     continue;
                 fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
                 fullPath = basePath + File.separator + fileName;
                 try {
                     fileItem.write(new File(fullPath));
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
            }
	}
        
        try {
        	
			List<List<Object>> contentList = ExcelUtil.readExcel(basePath, fileName);
			List<Map<String,Object>> entryList = FileUploadService.buildOrderExcelEntry(contentList);
			Map<String, List<Map<String,Object>>> m = FileUploadService.buildFinalEntryMap(entryList);
			fileUploadService.saveData(m);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
        AjaxResponse response = new AjaxResponse();
		response.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
		return response;
}
	
}
