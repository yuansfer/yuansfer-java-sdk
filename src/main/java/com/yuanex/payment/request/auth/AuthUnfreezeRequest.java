package com.yuanex.payment.request.auth;

import org.apache.commons.lang.StringUtils;

import com.yuanex.payment.exception.YuanpayException;
import com.yuanex.payment.request.ParamValidator;
import com.yuanex.payment.request.RequestConstants;
import com.yuanex.payment.request.YuanpayRequest;
import com.yuanex.payment.response.auth.AuthUnfreezeResponse;

import net.sf.json.JSONObject;

public class AuthUnfreezeRequest extends YuanpayRequest<AuthUnfreezeResponse> {

	private String outAuthInfoNo;
	private String outAuthDetailNo;
	private String unfreezeAmount;
	private String authIpnUrl;
	public String getOutAuthInfoNo() {
		return outAuthInfoNo;
	}
	public AuthUnfreezeRequest setOutAuthInfoNo(String outAuthInfoNo) {
		this.outAuthInfoNo = outAuthInfoNo;
		return this;
	}
	public String getOutAuthDetailNo() {
		return outAuthDetailNo;
	}
	public AuthUnfreezeRequest setOutAuthDetailNo(String outAuthDetailNo) {
		this.outAuthDetailNo = outAuthDetailNo;
		return this;
	}
	public String getUnfreezeAmount() {
		return unfreezeAmount;
	}
	public AuthUnfreezeRequest setUnfreezeAmount(String unfreezeAmount) {
		this.unfreezeAmount = unfreezeAmount;
		return this;
	}
	public String getAuthIpnUrl() {
		return authIpnUrl;
	}
	public AuthUnfreezeRequest setAuthIpnUrl(String authIpnUrl) {
		this.authIpnUrl = authIpnUrl;
		return this;
	}
	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.outAuthInfoNo)) {
			throw new YuanpayException("outAuthInfoNo missing");
		}
		if (StringUtils.isEmpty(this.outAuthDetailNo)) {
			throw new YuanpayException("outAuthDetailNo missing");
		}
		ParamValidator.amountValidate("unfreezeAmount", this.unfreezeAmount);
	}
	
	@Override
	protected String getAPIUrl(String env) {

		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.AUTH_UNFREEZE;
		return url;
	}
	
	@Override
	public AuthUnfreezeResponse convertResponse(String ret) {
		AuthUnfreezeResponse response = new AuthUnfreezeResponse();
		JSONObject json = JSONObject.fromObject(ret);
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		return response;
	}
	
	
	
}
