package com.yuansfer.payment.request.recurring;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.recurring.RevokeResponse;

import net.sf.json.JSONObject;

public class RevokeRequest extends YuanpayRequest<RevokeResponse> {

	private String autoDebitNo;

	public String getAutoDebitNo() {
		return autoDebitNo;
	}

	public RevokeRequest setAutoDebitNo(String autoDebitNo) {
		this.autoDebitNo = autoDebitNo;
		return this;
	}

	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.autoDebitNo)) {
			throw new YuanpayException("autoDebitNo missing");
		}
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.AUTO_REVOKE;
		return url;
	}

	@Override
	public RevokeResponse convertResponse(String ret) {
		RevokeResponse response = new RevokeResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}
	
	
	
}
