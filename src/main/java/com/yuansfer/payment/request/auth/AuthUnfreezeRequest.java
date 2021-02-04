package com.yuansfer.payment.request.auth;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.auth.AuthUnfreezeResponse;

import net.sf.json.JSONObject;

public class AuthUnfreezeRequest extends YuanpayRequest<AuthUnfreezeResponse> {

	private String authInfoNo;
	private String outAuthInfoNo;
	private String outAuthDetailNo;
	private String unfreezeAmount;
	private String authIpnUrl;
	private String currency;
	private String settleCurrency;
	
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
	
	
	public String getAuthInfoNo() {
		return authInfoNo;
	}
	public AuthUnfreezeRequest setAuthInfoNo(String authInfoNo) {
		this.authInfoNo = authInfoNo;
		return this;
	}
	public String getCurrency() {
		return currency;
	}
	public AuthUnfreezeRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	public String getSettleCurrency() {
		return settleCurrency;
	}
	public AuthUnfreezeRequest setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
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
			
		
		if (StringUtils.isEmpty(this.outAuthDetailNo)) {
			throw new YuanpayException("outAuthDetailNo missing");
		}
		
		if (StringUtils.isEmpty(this.currency)) {
			throw new YuanpayException("currency missing");
		}
		if (StringUtils.isEmpty(this.settleCurrency)) {
			throw new YuanpayException("settleCurrency missing");
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
