package com.yuanex.payment.request.offline;

import org.apache.commons.lang.StringUtils;

import com.yuanex.payment.exception.YuanpayException;
import com.yuanex.payment.request.ParamValidator;
import com.yuanex.payment.request.RequestConstants;
import com.yuanex.payment.request.YuanpayRequest;
import com.yuanex.payment.response.offline.InstoreAuthUnfreezeResponse;

import net.sf.json.JSONObject;

public class InstoreAuthUnfreezeRequest extends YuanpayRequest<InstoreAuthUnfreezeResponse> {

	private String transactionNo;
	private String reference;
	private String unfreezeAmount;
	
	
	public String getTransactionNo() {
		return transactionNo;
	}

	public InstoreAuthUnfreezeRequest setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
		return this;
	}

	public String getReference() {
		return reference;
	}

	public InstoreAuthUnfreezeRequest setReference(String reference) {
		this.reference = reference;
		return this;
	}

	public String getUnfreezeAmount() {
		return unfreezeAmount;
	}

	public InstoreAuthUnfreezeRequest setUnfreezeAmount(String unfreezeAmount) {
		this.unfreezeAmount = unfreezeAmount;
		return this;
	}

	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.transactionNo) && StringUtils.isEmpty(this.reference)) {
			throw new YuanpayException("transaction and reference cannot be null at the same time");
		}
		if (StringUtils.isNotEmpty(this.transactionNo) && StringUtils.isNotEmpty(this.reference)) {
			throw new YuanpayException("transaction and reference cannot exist at the same time");
		}
		ParamValidator.amountValidate("unfreezeAmount", this.unfreezeAmount);
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.INSTORE_AUTH_UNFREEZE;
		return url;
	}


	@Override
	public InstoreAuthUnfreezeResponse convertResponse(String ret) {
		InstoreAuthUnfreezeResponse response = new InstoreAuthUnfreezeResponse();
		JSONObject json = JSONObject.fromObject(ret);
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		
		if (null != json.get("transaction")) {
			response.setTransaction(json.getJSONObject("transaction"));
		}
		return response;
	}

	
}
