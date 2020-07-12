package com.yuansfer.payment.request;

import java.util.Map;

public class RequestBody {

	private String url;
	private Map<String, String> params;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	
}
