package com.yuanex.payment.request.auth;

import org.apache.commons.lang.StringUtils;

import com.yuanex.payment.enums.CurrencyEnums;
import com.yuanex.payment.exception.YuanpayException;
import com.yuanex.payment.request.ParamValidator;
import com.yuanex.payment.request.RequestConstants;
import com.yuanex.payment.request.YuanpayRequest;
import com.yuanex.payment.response.auth.AuthVoucherCreateResponse;

import net.sf.json.JSONObject;

public class AuthFreezeRequest extends YuanpayRequest<AuthVoucherCreateResponse> {

	private String amount;									//金额
	private String vendor;  								//渠道
    private String outAuthInfoNo;  							//商户预授权号
    private String outAuthDetailNo;  						//商户预授权操作号
    private String currency;  								//币种
    private String authIpnUrl;  							//预授权非扣款操作通知地址
	public String getVendor() {
		return vendor;
	}
	public AuthFreezeRequest setVendor(String vendor) {
		this.vendor = vendor;
		return this;
	}
	public String getOutAuthInfoNo() {
		return outAuthInfoNo;
	}
	public AuthFreezeRequest setOutAuthInfoNo(String outAuthInfoNo) {
		this.outAuthInfoNo = outAuthInfoNo;
		return this;
	}
	public String getOutAuthDetailNo() {
		return outAuthDetailNo;
	}
	public AuthFreezeRequest setOutAuthDetailNo(String outAuthDetailNo) {
		this.outAuthDetailNo = outAuthDetailNo;
		return this;
	}
	public String getCurrency() {
		return currency;
	}
	public AuthFreezeRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	public String getAuthIpnUrl() {
		return authIpnUrl;
	}
	public AuthFreezeRequest setAuthIpnUrl(String authIpnUrl) {
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
		if (StringUtils.isEmpty(this.vendor)) {
			throw new YuanpayException("vendor missing");
		}
		//币种校验
		if (StringUtils.isEmpty(this.currency)) {
			throw new YuanpayException("currency missing.");
		}
		if (!CurrencyEnums.USD.getValue().equals(this.currency)) {
			throw new YuanpayException("only USD is supported yet.");
		}
		ParamValidator.amountValidate("amount", this.amount);
	}
	
	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.AUTH_FREEAE;
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
