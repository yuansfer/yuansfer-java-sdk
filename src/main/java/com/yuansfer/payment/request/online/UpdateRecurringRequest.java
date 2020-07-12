package com.yuansfer.payment.request.online;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.online.UpdateRecurringResponse;

import net.sf.json.JSONObject;

public class UpdateRecurringRequest extends YuanpayRequest<UpdateRecurringResponse> {

	private Integer paymentCount;
	private String status;
	
	
	
	public Integer getPaymentCount() {
		return paymentCount;
	}

	public UpdateRecurringRequest setPaymentCount(Integer paymentCount) {
		this.paymentCount = paymentCount;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public UpdateRecurringRequest setStatus(String status) {
		this.status = status;
		return this;
	}

	
	@Override
	protected void dataValidate() {
		if (null == this.paymentCount) {
			throw new YuanpayException("paymentCount missing");
		}
		if (this.paymentCount <= 0) {
			throw new YuanpayException("data error: paymentCount");
		}
		if (StringUtils.isEmpty(this.status)) {
			throw new YuanpayException("status missing");
		}
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.UPDATE_RECURRING;
		return url;
	}


	@Override
	public UpdateRecurringResponse convertResponse(String ret) {
		UpdateRecurringResponse response = new UpdateRecurringResponse();
		JSONObject json = JSONObject.fromObject(ret);
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}
	
	
}
