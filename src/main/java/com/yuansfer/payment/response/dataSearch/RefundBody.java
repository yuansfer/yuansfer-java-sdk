package com.yuansfer.payment.response.dataSearch;

public class RefundBody {
	
	private String amount;									//原订单美金金额
	private String rmbAmount;								//原订单人民币金额
	private String refundAmount;							//退款美金金额
	private String refundRmbAmount;							//退款人民币金额
	private String currency;								//交易币种
	private String settleCurrency;							//结算币种，目前都是USD
	private String exchangeRate;							//汇率
	private String status;									//状态	
	private String reference;								//商户支付流水号
	private String refundTransactionId;						//退款订单号
	private String oldTransactionId;						//原付款订单号
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRmbAmount() {
		return rmbAmount;
	}
	public void setRmbAmount(String rmbAmount) {
		this.rmbAmount = rmbAmount;
	}
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	public String getRefundRmbAmount() {
		return refundRmbAmount;
	}
	public void setRefundRmbAmount(String refundRmbAmount) {
		this.refundRmbAmount = refundRmbAmount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSettleCurrency() {
		return settleCurrency;
	}
	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getRefundTransactionId() {
		return refundTransactionId;
	}
	public void setRefundTransactionId(String refundTransactionId) {
		this.refundTransactionId = refundTransactionId;
	}
	public String getOldTransactionId() {
		return oldTransactionId;
	}
	public void setOldTransactionId(String oldTransactionId) {
		this.oldTransactionId = oldTransactionId;
	}
	
}
