package com.yuansfer.payment.request.offline;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.offline.InstorePrepayResponse;

import net.sf.json.JSONObject;

public class InstorePrepayRequest extends YuanpayRequest<InstorePrepayResponse>{

	private String transactionNo;						//订单号
	private String reference;							//商户支付流水号
	private String paymentBarcode;						//支付码
	private String vendor;								//渠道
	
	public String getTransactionNo() {
		return transactionNo;
	}

	public InstorePrepayRequest setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
		return this;
	}

	public String getReference() {
		return reference;
	}

	public InstorePrepayRequest setReference(String reference) {
		this.reference = reference;
		return this;
	}

	public String getPaymentBarcode() {
		return paymentBarcode;
	}

	public InstorePrepayRequest setPaymentBarcode(String paymentBarcode) {
		this.paymentBarcode = paymentBarcode;
		return this;
	}

	public String getVendor() {
		return vendor;
	}

	public InstorePrepayRequest setVendor(String vendor) {
		this.vendor = vendor;
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
		if (StringUtils.isEmpty(this.paymentBarcode)) {
			throw new YuanpayException("paymentBarcode missing");
		}
		if (StringUtils.isEmpty(this.vendor)) {
			throw new YuanpayException("vendor missing");
		} 
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.INSTORE_PREPAY;
		return url;
	}


	@Override
	public InstorePrepayResponse convertResponse(String ret) {
		InstorePrepayResponse response = new InstorePrepayResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}
	
	
}
