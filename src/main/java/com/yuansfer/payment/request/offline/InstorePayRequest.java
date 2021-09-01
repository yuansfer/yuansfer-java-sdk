//package com.yuansfer.payment.request.offline;
//
//import org.apache.commons.lang.StringUtils;
//
//import com.yuansfer.payment.exception.YuanpayException;
//import com.yuansfer.payment.request.RequestConstants;
//import com.yuansfer.payment.request.YuanpayRequest;
//import com.yuansfer.payment.response.offline.InstorePayResponse;
//
//import net.sf.json.JSONObject;
//
//@Deprecated
//public class InstorePayRequest extends YuanpayRequest<InstorePayResponse>{
//
//	private String transactionNo;						//订单号
//	private String reference;							//商户支付流水号
//	private String paymentBarcode;						//支付码
//	private String vendor;								//渠道
//	
//	public String getTransactionNo() {
//		return transactionNo;
//	}
//
//	public InstorePayRequest setTransactionNo(String transactionNo) {
//		this.transactionNo = transactionNo;
//		return this;
//	}
//
//	public String getReference() {
//		return reference;
//	}
//
//	public InstorePayRequest setReference(String reference) {
//		this.reference = reference;
//		return this;
//	}
//
//	public String getPaymentBarcode() {
//		return paymentBarcode;
//	}
//
//	public InstorePayRequest setPaymentBarcode(String paymentBarcode) {
//		this.paymentBarcode = paymentBarcode;
//		return this;
//	}
//
//	public String getVendor() {
//		return vendor;
//	}
//
//	public InstorePayRequest setVendor(String vendor) {
//		this.vendor = vendor;
//		return this;
//	}
//
//
//	@Override
//	protected void dataValidate() {
//		if (StringUtils.isEmpty(this.transactionNo) && StringUtils.isEmpty(this.reference)) {
//			throw new YuanpayException("transaction and reference cannot be null at the same time");
//		}
//		if (StringUtils.isNotEmpty(this.transactionNo) && StringUtils.isNotEmpty(this.reference)) {
//			throw new YuanpayException("transaction and reference cannot exist at the same time");
//		}
//		if (StringUtils.isEmpty(this.paymentBarcode)) {
//			throw new YuanpayException("paymentBarcode missing");
//		}
//		if (StringUtils.isEmpty(this.vendor)) {
//			throw new YuanpayException("vendor missing");
//		} 
//	}
//
//	@Override
//	protected String getAPIUrl(String env) {
//		String urlPrefix = getUrlPrefix(env);
//		String url = urlPrefix + RequestConstants.INSTORE_PAY;
//		return url;
//	}
//
//
//	@Override
//	public InstorePayResponse convertResponse(String ret) {
//		InstorePayResponse response = new InstorePayResponse();
//		JSONObject json = JSONObject.fromObject(ret);
//		if (null != json.get("result")) {
//			response.setResult(json.getJSONObject("result"));
//		}
//		response.setRetCode(json.getString("ret_code"));
//		response.setRetMsg(json.getString("ret_msg"));
//		return response;
//	}
//	
//	
//}
