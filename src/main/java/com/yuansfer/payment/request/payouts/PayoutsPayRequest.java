package com.yuansfer.payment.request.payouts;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.payouts.PayoutsPayResponse;

import net.sf.json.JSONObject;

public class PayoutsPayRequest extends YuanpayRequest<PayoutsPayResponse> {

	private String invoiceId;
	
	private String subject;
	
	private String currency;
	
	private String amount;
	
	private String vendor;
	
	private String ipnUrl;
	
	private String description;
	
	private String note;
	
	private String language;
	
	private String customerNo;
	
	private String receiver;
	
	private String receiverType;

	public String getInvoiceId() {
		return invoiceId;
	}

	public PayoutsPayRequest setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
		return this;
	}

	public String getSubject() {
		return subject;
	}

	public PayoutsPayRequest setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public String getCurrency() {
		return currency;
	}

	public PayoutsPayRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public String getAmount() {
		return amount;
	}

	public PayoutsPayRequest setAmount(String amount) {
		this.amount = amount;
		return this;
	}

	public String getVendor() {
		return vendor;
	}

	public PayoutsPayRequest setVendor(String vendor) {
		this.vendor = vendor;
		return this;
	}

	public String getIpnUrl() {
		return ipnUrl;
	}

	public PayoutsPayRequest setIpnUrl(String ipnUrl) {
		this.ipnUrl = ipnUrl;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public PayoutsPayRequest setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getNote() {
		return note;
	}

	public PayoutsPayRequest setNote(String note) {
		this.note = note;
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public PayoutsPayRequest setLanguage(String language) {
		this.language = language;
		return this;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public PayoutsPayRequest setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
		return this;
	}

	public String getReceiver() {
		return receiver;
	}

	public PayoutsPayRequest setReceiver(String receiver) {
		this.receiver = receiver;
		return this;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public PayoutsPayRequest setReceiverType(String receiverType) {
		this.receiverType = receiverType;
		return this;
	}

	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.invoiceId)) {
			throw new YuanpayException("invoiceId missing");
		}
		
		if (StringUtils.isEmpty(this.subject)) {
			throw new YuanpayException("subject missing");
		}
		
		if (StringUtils.isEmpty(this.currency)) {
			throw new YuanpayException("currency missing");
		}
		
		if (StringUtils.isEmpty(this.amount)) {
			throw new YuanpayException("amount missing");
		}
		
		if (StringUtils.isEmpty(this.vendor)) {
			throw new YuanpayException("vendor missing");
		}
		
		if (StringUtils.isEmpty(this.description)) {
			throw new YuanpayException("description missing");
		}
		
		if (StringUtils.isEmpty(this.receiver)) {
			throw new YuanpayException("receiver missing");
		}
		
		if (StringUtils.isEmpty(this.receiverType)) {
			throw new YuanpayException("receiverType missing");
		}
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.PAYOUTS_PAY;
		return url;
	}

	@Override
	public PayoutsPayResponse convertResponse(String ret) {
		PayoutsPayResponse response = new PayoutsPayResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}
	
	
	
}
