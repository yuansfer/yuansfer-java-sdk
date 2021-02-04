package com.yuansfer.payment.request.recurring;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.recurring.ConsultResponse;

import net.sf.json.JSONObject;

public class ConsultRequest extends YuanpayRequest<ConsultResponse> {

	private String osType;
	
	private String osVersion;
	
	private String autoIpnUrl;
	
	private String autoRedirectUrl;
	
	private String autoReference;
	
	private String vendor;
	
	private String terminal;

	public String getOsType() {
		return osType;
	}

	public ConsultRequest setOsType(String osType) {
		this.osType = osType;
		return this;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public ConsultRequest setOsVersion(String osVersion) {
		this.osVersion = osVersion;
		return this;
	}

	public String getAutoIpnUrl() {
		return autoIpnUrl;
	}

	public ConsultRequest setAutoIpnUrl(String autoIpnUrl) {
		this.autoIpnUrl = autoIpnUrl;
		return this;
	}

	public String getAutoRedirectUrl() {
		return autoRedirectUrl;
	}

	public ConsultRequest setAutoRedirectUrl(String autoRedirectUrl) {
		this.autoRedirectUrl = autoRedirectUrl;
		return this;
	}

	public String getAutoReference() {
		return autoReference;
	}

	public ConsultRequest setAutoReference(String autoReference) {
		this.autoReference = autoReference;
		return this;
	}

	public String getVendor() {
		return vendor;
	}

	public ConsultRequest setVendor(String vendor) {
		this.vendor = vendor;
		return this;
	}

	public String getTerminal() {
		return terminal;
	}

	public ConsultRequest setTerminal(String terminal) {
		this.terminal = terminal;
		return this;
	}

	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.osType)) {
			throw new YuanpayException("osType missing");
		}
		
		if (StringUtils.isEmpty(this.osVersion)) {
			throw new YuanpayException("osVersion missing");
		}
		
		if (StringUtils.isEmpty(this.autoRedirectUrl)) {
			throw new YuanpayException("autoRedirectUrl missing");
		}
		
		if (StringUtils.isEmpty(this.autoReference)) {
			throw new YuanpayException("autoReference missing");
		}
		
		if (StringUtils.isEmpty(this.vendor)) {
			throw new YuanpayException("vendor missing");
		}
		
		if (StringUtils.isEmpty(this.terminal)) {
			throw new YuanpayException("terminal missing");
		}
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.AUTO_CONSULT;
		return url;
	}

	@Override
	public ConsultResponse convertResponse(String ret) {
		ConsultResponse response = new ConsultResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}
	
	
}
