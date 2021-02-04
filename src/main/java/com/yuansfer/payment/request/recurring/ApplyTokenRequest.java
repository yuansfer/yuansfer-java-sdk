package com.yuansfer.payment.request.recurring;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.recurring.ApplyTokenResponse;

import net.sf.json.JSONObject;

public class ApplyTokenRequest extends YuanpayRequest<ApplyTokenResponse> {

	private String autoDebitNo;
	
	private String grantType;

	public String getAutoDebitNo() {
		return autoDebitNo;
	}

	public ApplyTokenRequest setAutoDebitNo(String autoDebitNo) {
		this.autoDebitNo = autoDebitNo;
		return this;
	}

	public String getGrantType() {
		return grantType;
	}

	public ApplyTokenRequest setGrantType(String grantType) {
		this.grantType = grantType;
		return this;
	}

	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.autoDebitNo)) {
			throw new YuanpayException("autoDebitNo missing");
		}
		if (StringUtils.isEmpty(this.grantType)) {
			throw new YuanpayException("grantType missing");
		}
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.AUTO_APPLY_TOKEN;
		return url;
	}

	@Override
	public ApplyTokenResponse convertResponse(String ret) {
		ApplyTokenResponse response = new ApplyTokenResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}
	
	
	
}
