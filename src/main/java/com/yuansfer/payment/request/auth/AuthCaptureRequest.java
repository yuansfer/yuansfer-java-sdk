package com.yuansfer.payment.request.auth;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.auth.AuthCaptureResponse;

import net.sf.json.JSONObject;

public class AuthCaptureRequest extends YuanpayRequest<AuthCaptureResponse> {

	private String authInfoNo;
	private String outAuthInfoNo;
	private String outAuthDetailNo;
	private String amount;
	private String ipnUrl;
	private String currency;
	private String settleCurrency;
	private String authConfirmModel;
	private String reference;
	
	public String getOutAuthInfoNo() {
		return outAuthInfoNo;
	}
	public AuthCaptureRequest setOutAuthInfoNo(String outAuthInfoNo) {
		this.outAuthInfoNo = outAuthInfoNo;
		return this;
	}
	public String getOutAuthDetailNo() {
		return outAuthDetailNo;
	}
	public AuthCaptureRequest setOutAuthDetailNo(String outAuthDetailNo) {
		this.outAuthDetailNo = outAuthDetailNo;
		return this;
	}
	public String getAmount() {
		return amount;
	}
	public AuthCaptureRequest setAmount(String amount) {
		this.amount = amount;
		return this;
	}
	public String getIpnUrl() {
		return ipnUrl;
	}
	public AuthCaptureRequest setIpnUrl(String ipnUrl) {
		this.ipnUrl = ipnUrl;
		return this;
	}
	
	public String getAuthInfoNo() {
		return authInfoNo;
	}
	public AuthCaptureRequest setAuthInfoNo(String authInfoNo) {
		this.authInfoNo = authInfoNo;
		return this;
	}
	public String getCurrency() {
		return currency;
	}
	public AuthCaptureRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	public String getSettleCurrency() {
		return settleCurrency;
	}
	public AuthCaptureRequest setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
		return this;
	}
	public String getAuthConfirmModel() {
		return authConfirmModel;
	}
	public AuthCaptureRequest setAuthConfirmModel(String authConfirmModel) {
		this.authConfirmModel = authConfirmModel;
		return this;
	}
	
	public String getReference() {
		return reference;
	}
	public AuthCaptureRequest setReference(String reference) {
		this.reference = reference;
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
		if (StringUtils.isEmpty(this.reference)) {
			throw new YuanpayException("reference missing");
		}
		
		ParamValidator.amountValidate("amount", this.amount);
	}
	
	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.AUTH_CAPTURE;
		return url;
	}
	@Override
	public AuthCaptureResponse convertResponse(String ret) {
		AuthCaptureResponse response = new AuthCaptureResponse();
		JSONObject json = JSONObject.fromObject(ret);
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		return response;
	}
}
