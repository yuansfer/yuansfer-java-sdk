package com.yuanex.payment.response.offline;

import com.yuanex.payment.response.YuanpayResponse;

public class InstoreCreateTranQrcodeResponse extends YuanpayResponse {

	private static final long serialVersionUID = -6556411072876231793L;
	
	private String transactionNo;						//订单号
	private String reference;							//商户支付流水号
	private String amount;								//美金金额
	private Integer timeout;							//超时时间
	private String deepLink;							//支付链接
	private String qrcodeUrl;							//支付链接转化成的二维码的链接
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public String getDeepLink() {
		return deepLink;
	}
	public void setDeepLink(String deepLink) {
		this.deepLink = deepLink;
	}
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}
	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}
	
}
