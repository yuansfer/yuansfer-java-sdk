package com.yuanex.payment.request.auth;

import org.apache.commons.lang.StringUtils;

import com.yuanex.payment.exception.YuanpayException;
import com.yuanex.payment.request.ParamValidator;
import com.yuanex.payment.request.RequestConstants;
import com.yuanex.payment.request.YuanpayRequest;
import com.yuanex.payment.response.auth.AuthCaptureResponse;

import net.sf.json.JSONObject;

public class AuthCaptureRequest extends YuanpayRequest<AuthCaptureResponse> {

	private String outAuthInfoNo;
	private String outAuthDetailNo;
	private String amount;
	private String ipnUrl;
	
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
	
	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.outAuthInfoNo)) {
			throw new YuanpayException("outAuthInfoNo missing");
		}
		if (StringUtils.isEmpty(this.outAuthDetailNo)) {
			throw new YuanpayException("outAuthDetailNo missing");
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
