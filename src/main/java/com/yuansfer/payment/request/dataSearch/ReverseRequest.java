package com.yuansfer.payment.request.dataSearch;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.dataSearch.ReverseResponse;

import net.sf.json.JSONObject;

public class ReverseRequest extends YuanpayRequest<ReverseResponse> {

	private String transactionNo;
	private String reference;

	public String getTransactionNo() {
		return transactionNo;
	}

	public ReverseRequest setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
		return this;
	}

	public String getReference() {
		return reference;
	}

	public ReverseRequest setReference(String reference) {
		this.reference = reference;
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
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.REVERSE;
		return url;
	}


	@Override
	public ReverseResponse convertResponse(String ret) {
		ReverseResponse response = new ReverseResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}

	
}
