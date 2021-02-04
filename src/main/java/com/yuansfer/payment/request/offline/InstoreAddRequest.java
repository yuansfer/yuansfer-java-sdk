package com.yuansfer.payment.request.offline;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.offline.InstoreAddResponse;

import net.sf.json.JSONObject;

public class InstoreAddRequest  extends YuanpayRequest<InstoreAddResponse> {

	private String amount;								//美金金额
	private String currency;							//币种
	private String settleCurrency;
	private String reference;							//商户支付流水号
	
	public String getAmount() {
		return amount;
	}

	public InstoreAddRequest setAmount(String amount) {
		this.amount = amount;
		return this;
	}

	public String getCurrency() {
		return currency;
	}

	public InstoreAddRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	public String getSettleCurrency() {
		return settleCurrency;
	}

	public InstoreAddRequest setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
		return this;
	}

	public String getReference() {
		return reference;
	}

	public InstoreAddRequest setReference(String reference) {
		this.reference = reference;
		return this;
	}

	@Override
	protected void dataValidate() {
		ParamValidator.amountValidate("amount", this.amount);
		
		//币种校验
		if (StringUtils.isEmpty(this.currency)) {
			throw new YuanpayException("currency missing.");
		}
		if (StringUtils.isEmpty(this.settleCurrency)) {
			throw new YuanpayException("settleCurrency missing");
		}
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.INSTORE_ADD;
		return url;
	}

	@Override
	public InstoreAddResponse convertResponse(String ret) {
		InstoreAddResponse response = new InstoreAddResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}

	

}
