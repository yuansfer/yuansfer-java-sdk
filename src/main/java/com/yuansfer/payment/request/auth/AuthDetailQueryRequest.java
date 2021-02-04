package com.yuansfer.payment.request.auth;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.auth.AuthDetailQueryResponse;

import net.sf.json.JSONObject;

public class AuthDetailQueryRequest extends YuanpayRequest<AuthDetailQueryResponse> {

	private String authInfoNo;
	
	private String outAuthInfoNo;
	
	private String authDetailNo;
	
	private String outAuthDetailNo;

	public String getAuthInfoNo() {
		return authInfoNo;
	}

	public AuthDetailQueryRequest setAuthInfoNo(String authInfoNo) {
		this.authInfoNo = authInfoNo;
		return this;
	}

	public String getOutAuthInfoNo() {
		return outAuthInfoNo;
	}

	public AuthDetailQueryRequest setOutAuthInfoNo(String outAuthInfoNo) {
		this.outAuthInfoNo = outAuthInfoNo;
		return this;
	}

	public String getAuthDetailNo() {
		return authDetailNo;
	}

	public AuthDetailQueryRequest setAuthDetailNo(String authDetailNo) {
		this.authDetailNo = authDetailNo;
		return this;
	}

	public String getOutAuthDetailNo() {
		return outAuthDetailNo;
	}

	public AuthDetailQueryRequest setOutAuthDetailNo(String outAuthDetailNo) {
		this.outAuthDetailNo = outAuthDetailNo;
		return this;
	}

	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.outAuthInfoNo) 
				&& StringUtils.isEmpty(this.authInfoNo)) {
			throw new YuanpayException("outAuthInfoNo and authInfoNo missing");
		}
		
		if (StringUtils.isNotEmpty(this.outAuthDetailNo) 
				&& StringUtils.isNotEmpty(this.authInfoNo)) {
			throw new YuanpayException("outAuthInfoNo and authInfoNo cannot exist at the same time");
		}
		
		if (StringUtils.isEmpty(this.outAuthDetailNo) 
				&& StringUtils.isEmpty(this.authDetailNo)) {
			throw new YuanpayException("outAuthDetailNo and authDetailNo missing");
		}
		
		if (StringUtils.isNotEmpty(this.outAuthDetailNo) 
				&& StringUtils.isNotEmpty(this.authDetailNo)) {
			throw new YuanpayException("outAuthDetailNo and authDetailNo cannot exist at the same time");
		}
		
		
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.AUTH_DETAIL_QUERY;
		return url;
	}

	@Override
	public AuthDetailQueryResponse convertResponse(String ret) {
		AuthDetailQueryResponse response = new AuthDetailQueryResponse();
		JSONObject json = JSONObject.fromObject(ret);
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		return response;
	}
	
	
}
