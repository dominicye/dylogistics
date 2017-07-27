package com.qiaoyu.app.common;

public class AjaxResponse {
	
	public static final String RESPONSE_TYPE_SUCCESS = "SUCCESS";
	public static final String RESPONSE_TYPE_FAIL = "FAIL";
	
	private String status = null;
	private Object result = null;

	
	public AjaxResponse(){
		super();
	}
	
	public AjaxResponse(String status, Object result){
		super();
		this.status = status;
		this.result = result;
	}

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

	public static String getRESPONSE_TYPE_SUCCESS() {
		return RESPONSE_TYPE_SUCCESS;
	}

	public static String getRESPONSE_TYPE_FAIL() {
		return RESPONSE_TYPE_FAIL;
	}

}
