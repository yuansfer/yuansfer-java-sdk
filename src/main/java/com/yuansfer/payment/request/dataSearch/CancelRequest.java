package com.yuansfer.payment.request.dataSearch;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.dataSearch.CancelResponse;

import net.sf.json.JSONObject;

public class CancelRequest extends YuanpayRequest<CancelResponse> {

	private String transactionNo;
	private String reference;

	public String getTransactionNo() {
		return transactionNo;
	}

	public CancelRequest setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
		return this;
	}

	public String getReference() {
		return reference;
	}

	public CancelRequest setReference(String reference) {
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
		String url = urlPrefix + RequestConstants.CANCEL;
		return url;
	}


	@Override
	public CancelResponse convertResponse(String ret) {
		CancelResponse response = new CancelResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}

	
}
