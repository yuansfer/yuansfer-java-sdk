package com.yuanex.payment.request.dataSearch;

import com.yuanex.payment.request.ParamValidator;
import com.yuanex.payment.request.RequestConstants;
import com.yuanex.payment.request.YuanpayRequest;
import com.yuanex.payment.response.dataSearch.DataStatusResponse;

import net.sf.json.JSONObject;

public class DataStatusRequest extends YuanpayRequest<DataStatusResponse> {

	private String paymentDate;

	public String getPaymentDate() {
		return paymentDate;
	}

	public DataStatusRequest setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
		return this;
	}

	
	@Override
	protected void dataValidate() {
		ParamValidator.dateValidate("paymentDate", this.paymentDate);
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.DATA_STATUS;
		return url;
	}

	@Override
	public DataStatusResponse convertResponse(String ret) {
		DataStatusResponse response = new DataStatusResponse();
		JSONObject json = JSONObject.fromObject(ret);
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		return response;
	}
	
	
}
