package com.yuansfer.payment.request.dataSearch;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.dataSearch.RefundResponse;

import net.sf.json.JSONObject;

public class RefundRequest extends YuanpayRequest<RefundResponse> {

	private String refundAmount;							//美金金额	
	private String transactionNo;					//订单号
	private String reference;						//商户支付流水号
	private String refundReference;					//退款流水号
	private String currency;
	private String settleCurrency;
	
	
	public String getRefundAmount() {
		return refundAmount;
	}

	public RefundRequest setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
		return this;
	}


	public String getTransactionNo() {
		return transactionNo;
	}

	public RefundRequest setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
		return this;
	}

	public String getReference() {
		return reference;
	}

	public RefundRequest setReference(String reference) {
		this.reference = reference;
		return this;
	}
	
	public String getRefundReference() {
		return refundReference;
	}

	public RefundRequest setRefundReference(String refundReference) {
		this.refundReference = refundReference;
		return this;
	}
	public String getCurrency() {
		return currency;
	}

	public RefundRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	public String getSettleCurrency() {
		return settleCurrency;
	}

	public RefundRequest setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
		return this;
	}

	@Override
	protected void dataValidate() {
		ParamValidator.amountValidate("amount", this.refundAmount);
		
		if (StringUtils.isEmpty(this.currency)) {
			throw new YuanpayException("currency missing");
		}
		if (StringUtils.isEmpty(this.settleCurrency)) {
			throw new YuanpayException("settleCurrency missing");
		}
		
		
		if (StringUtils.isEmpty(this.transactionNo) && StringUtils.isEmpty(this.reference)) {
			throw new RuntimeException("transaction and reference cannnot be null at the same time");
		}
		if (StringUtils.isNotEmpty(this.transactionNo) && StringUtils.isNotEmpty(this.reference)) {
			throw new RuntimeException("transaction and reference cannot exist at the same time");
		}
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.REFUND;
		return url;
	}


	@Override
	public RefundResponse convertResponse(String ret) {
		RefundResponse response = new RefundResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}

}
