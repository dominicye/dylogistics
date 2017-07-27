package com.qiaoyu.app.json;

public class JsonResult {
	
	public static final String STATUS_SUCCESS = "success";
	
	public static final String STATUS_FAILED = "failed";
	
	private String status;
	
	private Object result;
	
	private Exception e;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}
	
	

}
