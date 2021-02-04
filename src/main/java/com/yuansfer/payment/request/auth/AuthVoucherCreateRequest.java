package com.yuansfer.payment.request.auth;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.auth.AuthVoucherCreateResponse;

import net.sf.json.JSONObject;

public class AuthVoucherCreateRequest extends YuanpayRequest<AuthVoucherCreateResponse> {

	private String outAuthInfoNo;					//商户系统预授权号
	private String outAuthDetailNo;					//商户系统预授权操作号
	private String vendor;
	private String amount;
	private String currency;
	private String settleCurrency;
	
	public String getOutAuthInfoNo() {
		return outAuthInfoNo;
	}
	public AuthVoucherCreateRequest setOutAuthInfoNo(String outAuthInfoNo) {
		this.outAuthInfoNo = outAuthInfoNo;
		return this;
	}
	public String getOutAuthDetailNo() {
		return outAuthDetailNo;
	}
	public AuthVoucherCreateRequest setOutAuthDetailNo(String outAuthDetailNo) {
		this.outAuthDetailNo = outAuthDetailNo;
		return this;
	}
	public String getVendor() {
		return vendor;
	}
	public AuthVoucherCreateRequest setVendor(String vendor) {
		this.vendor = vendor;
		return this;
	}
	public String getAmount() {
		return amount;
	}
	public AuthVoucherCreateRequest setAmount(String amount) {
		this.amount = amount;
		return this;
	}
	public String getCurrency() {
		return currency;
	}
	public AuthVoucherCreateRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	
	public String getSettleCurrency() {
		return settleCurrency;
	}
	public AuthVoucherCreateRequest setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
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
		if (StringUtils.isEmpty(this.vendor)) {
			throw new YuanpayException("vendor missing");
		}
		ParamValidator.amountValidate("amount", this.amount);
	}
	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.AUTH_VOUCHER_CREATE;
		return url;
	}
	@Override
	public AuthVoucherCreateResponse convertResponse(String ret) {
		AuthVoucherCreateResponse response = new AuthVoucherCreateResponse();
		JSONObject json = JSONObject.fromObject(ret);
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		return response;
	}
}
