package com.yuanex.payment.request.offline;

import org.apache.commons.lang.StringUtils;

import com.yuanex.payment.enums.CurrencyEnums;
import com.yuanex.payment.exception.YuanpayException;
import com.yuanex.payment.request.ParamValidator;
import com.yuanex.payment.request.RequestConstants;
import com.yuanex.payment.request.YuanpayRequest;
import com.yuanex.payment.response.offline.InstoreCashierAddResponse;

import net.sf.json.JSONObject;

public class InstoreCashierAddRequest extends YuanpayRequest<InstoreCashierAddResponse> {

	private String amount;
	private String currency;
	private String reference;
	private String ipnUrl;
	
	
	public String getAmount() {
		return amount;
	}

	public InstoreCashierAddRequest setAmount(String amount) {
		this.amount = amount;
		return this;
	}

	public String getCurrency() {
		return currency;
	}

	public InstoreCashierAddRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public String getReference() {
		return reference;
	}

	public InstoreCashierAddRequest setReference(String reference) {
		this.reference = reference;
		return this;
	}

	public String getIpnUrl() {
		return ipnUrl;
	}

	public InstoreCashierAddRequest setIpnUrl(String ipnUrl) {
		this.ipnUrl = ipnUrl;
		return this;
	}

	@Override
	protected void dataValidate() {
		ParamValidator.amountValidate("amount", this.amount);
		
		//币种校验
		if (StringUtils.isEmpty(this.currency)) {
			throw new YuanpayException("currency missing.");
		}
		if (!CurrencyEnums.USD.getValue().equals(this.currency)) {
			throw new YuanpayException("only USD is supported yet.");
		}
		if (StringUtils.isEmpty(this.reference)) {
			throw new YuanpayException("reference missing");
		}
		if (StringUtils.isEmpty(this.ipnUrl)) {
			throw new YuanpayException("ipnUrl missing");
		}
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.INSTORE_CASHIER_ADD;
		return url;
	}

	@Override
	public InstoreCashierAddResponse convertResponse(String ret) {
		InstoreCashierAddResponse response = new InstoreCashierAddResponse();
		JSONObject json = JSONObject.fromObject(ret);
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		
		if (null != json.get("transaction")) {
			if (null != json.get("transaction")) {
				response.setTransaction(json.getJSONObject("transaction"));
			}
		}
		return response;
	}

	
}
