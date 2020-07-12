package com.yuansfer.payment.response.dataSearch;

/*
 * online 付款订单对象中的退款信息
 */
public class RefundInfoBody {
	
	private String refundTransactionId;						//退款订单号
	private String refundAmount;							//退款美金金额
	private String refundRmbAmount;							//退款人民币金额
	private String currency;								//币种	
	private String settleCurrency;							//结算币种
	
	public String getRefundTransactionId() {
		return refundTransactionId;
	}
	public void setRefundTransactionId(String refundTransactionId) {
		this.refundTransactionId = refundTransactionId;
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
}
