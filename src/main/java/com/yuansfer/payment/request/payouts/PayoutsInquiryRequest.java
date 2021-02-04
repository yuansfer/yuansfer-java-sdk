package com.yuansfer.payment.request.payouts;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.payouts.PayoutsInquiryResponse;

import net.sf.json.JSONObject;

public class PayoutsInquiryRequest extends YuanpayRequest<PayoutsInquiryResponse> {

	private String invoiceId;

	public String getInvoiceId() {
		return invoiceId;
	}

	public PayoutsInquiryRequest setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
		return this;
	}

	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.invoiceId)) {
			throw new YuanpayException("invoiceId missing");
		}
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.PAYOUTS_INQUIRY;
		return url;
	}

	@Override
	public PayoutsInquiryResponse convertResponse(String ret) {
		PayoutsInquiryResponse response = new PayoutsInquiryResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}
	
	
	
}
