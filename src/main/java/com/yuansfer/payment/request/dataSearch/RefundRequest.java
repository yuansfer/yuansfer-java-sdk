package com.yuansfer.payment.request.dataSearch;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.dataSearch.RefundBody;
import com.yuansfer.payment.response.dataSearch.RefundResponse;

import net.sf.json.JSONObject;

public class RefundRequest extends YuanpayRequest<RefundResponse> {

	private String amount;							//美金金额	
	private String rmbAmount;						//人民币金额
	private String transactionNo;					//订单号
	private String reference;						//商户支付流水号
	private String refundReference;					//退款流水号
	
	
	public String getAmount() {
		return amount;
	}

	public RefundRequest setAmount(String amount) {
		this.amount = amount;
		return this;
	}

	public String getRmbAmount() {
		return rmbAmount;
	}

	public RefundRequest setRmbAmount(String rmbAmount) {
		this.rmbAmount = rmbAmount;
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


	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.amount) && StringUtils.isEmpty(this.rmbAmount)) {
			throw new RuntimeException("amount and rmbAmount cannnot be null at the same time.");
		} else if (StringUtils.isNotEmpty(this.amount) && StringUtils.isNotEmpty(this.rmbAmount)) {
			throw new RuntimeException("amount and rmbAmount can't exist at the same time.");
		} else if (StringUtils.isNotEmpty(this.amount)) {
			ParamValidator.amountValidate("amount", this.amount);
		} else {
			ParamValidator.amountValidate("rmbAmount", this.rmbAmount);
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
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		
		if (null != json.get("result")) {
			JSONObject resultJson = json.getJSONObject("result");
			RefundBody result = (RefundBody)JSONObject.toBean(resultJson, RefundBody.class);
			response.setResult(result);
		}
		return response;
	}

}
