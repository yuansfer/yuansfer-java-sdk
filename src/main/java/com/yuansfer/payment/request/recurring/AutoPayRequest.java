package com.yuansfer.payment.request.recurring;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.recurring.AutoPayResponse;

import net.sf.json.JSONObject;

public class AutoPayRequest extends YuanpayRequest<AutoPayResponse> {

	private String autoDebitNo;
	
	private String amount;
	
	private String currency;
	
	private String settleCurrency;
	
	private String reference;
	
	private String ipnUrl;

	public String getAutoDebitNo() {
		return autoDebitNo;
	}

	public AutoPayRequest setAutoDebitNo(String autoDebitNo) {
		this.autoDebitNo = autoDebitNo;
		return this;
	}

	public String getAmount() {
		return amount;
	}

	public AutoPayRequest setAmount(String amount) {
		this.amount = amount;
		return this;
	}

	public String getCurrency() {
		return currency;
	}

	public AutoPayRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public String getSettleCurrency() {
		return settleCurrency;
	}

	public AutoPayRequest setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
		return this;
	}

	public String getReference() {
		return reference;
	}

	public AutoPayRequest setReference(String reference) {
		this.reference = reference;
		return this;
	}

	public String getIpnUrl() {
		return ipnUrl;
	}

	public AutoPayRequest setIpnUrl(String ipnUrl) {
		this.ipnUrl = ipnUrl;
		return this;
	}

	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.autoDebitNo)) {
			throw new YuanpayException("autoDebitNo missing");
		}
		
		ParamValidator.amountValidate("amount", this.amount);
		
		if (StringUtils.isEmpty(this.currency)) {
			throw new YuanpayException("currency missing");
		}
		
		if (StringUtils.isEmpty(this.settleCurrency)) {
			throw new YuanpayException("settleCurrency missing");
		}
		
		if (StringUtils.isEmpty(this.reference)) {
			throw new YuanpayException("reference missing");
		}
		
		
 	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.AUTO_PAY;
		return url;
	}

	@Override
	public AutoPayResponse convertResponse(String ret) {
		AutoPayResponse response = new AutoPayResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}
	
	
	
}
