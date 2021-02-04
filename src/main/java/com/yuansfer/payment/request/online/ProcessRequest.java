package com.yuansfer.payment.request.online;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.online.ProcessResponse;

import net.sf.json.JSONObject;

public class ProcessRequest extends YuanpayRequest<ProcessResponse> {

	
	private String paymentMethodNonce;
	
	private String paymentMethod;
	
	private String transactionNo;
	
	
	
	public String getPaymentMethodNonce() {
		return paymentMethodNonce;
	}

	public ProcessRequest setPaymentMethodNonce(String paymentMethodNonce) {
		this.paymentMethodNonce = paymentMethodNonce;
		return this;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public ProcessRequest setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
		return this;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public ProcessRequest setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
		return this;
	}
	

	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.paymentMethodNonce)) {
			throw new YuanpayException("paymentMethodNonce missing");
		}  
		if (StringUtils.isEmpty(this.paymentMethod)) {
			throw new YuanpayException("paymentMethod missing");
		}  
		if (StringUtils.isEmpty(this.transactionNo)) {
			throw new YuanpayException("transactionNo missing");
		}  
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.PROCESS;
		return url;
	}

	@Override
	public ProcessResponse convertResponse(String ret) {
		ProcessResponse response = new ProcessResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}

}
