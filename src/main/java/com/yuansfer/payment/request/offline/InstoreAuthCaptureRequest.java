package com.yuansfer.payment.request.offline;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.offline.InstoreAuthCaptureResponse;

import net.sf.json.JSONObject;

public class InstoreAuthCaptureRequest extends YuanpayRequest<InstoreAuthCaptureResponse> {

	private String transactionNo;
	private String reference;
	private String amount;
	
	public String getTransactionNo() {
		return transactionNo;
	}

	public InstoreAuthCaptureRequest setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
		return this;
	}

	public String getReference() {
		return reference;
	}

	public InstoreAuthCaptureRequest setReference(String reference) {
		this.reference = reference;
		return this;
	}

	public String getAmount() {
		return amount;
	}

	public InstoreAuthCaptureRequest setAmount(String amount) {
		this.amount = amount;
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
		ParamValidator.amountValidate("amount", this.amount);
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.INSTORE_AUTH_CAPTURE;
		return url;
	}


	@Override
	public InstoreAuthCaptureResponse convertResponse(String ret) {
		InstoreAuthCaptureResponse response = new InstoreAuthCaptureResponse();
		JSONObject json = JSONObject.fromObject(ret);
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		
		if (null != json.get("transaction")) {
			response.setTransaction(json.getJSONObject("transaction"));
		}
		return response;
	}

	
}
