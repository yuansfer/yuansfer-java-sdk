package com.yuansfer.payment.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.config.YuanpayConfig;
import com.yuansfer.payment.enums.EnviromentEnums;
import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.response.YuanpayResponse;
import com.yuansfer.payment.utils.MapUrlUtils;
import com.yuansfer.payment.utils.Md5Utils;
import com.yuansfer.payment.utils.ReflectUtils;

/**
 * @author yuansfer
 */
public abstract class YuanpayRequest<T extends YuanpayResponse> {
	
	protected YuanpayConfig config;
	
	/*
	 * 关联配置
	 */
	public RequestBody initRequestBody(YuanpayConfig yuanpayConfig) {
		//设置config
		this.config = yuanpayConfig;
		//数据娇艳
		dataValidate();
		//设置url
		String url = getAPIUrl(this.config.getEnv());
		//参数设置
		Map<String, String> params = convertToRequestParam();
		//组装返回
		RequestBody requestBody = new RequestBody();
		requestBody.setUrl(url);
		requestBody.setParams(params);
		return requestBody;
	}
	
	protected String getUrlPrefix(String env) {
		String urlPrefix = null;
		if (EnviromentEnums.SANDBOX.getValue().equals(env)) {
			urlPrefix = RequestConstants.SANDBOX_PREFIX;
		} else if (EnviromentEnums.PRODUCT.getValue().equals(env)) {
			urlPrefix = RequestConstants.PRODUCT_PREFIX;
		} else {
			throw new YuanpayException("env error");
		}
		return urlPrefix;
	}
	
	
	/*
	 * 请求信息转换
	 */
	protected Map<String, String> convertToRequestParam() {
		if (null == this.config) {
			throw new YuanpayException("missing config information.");
		}
		
		List<String> ignores = new ArrayList<>();
		ignores.add("config");
		Map<String, String> map = ReflectUtils.obj2map(this, ignores);
		
		map = this.config.basicParamSetting(map);
		
		if (StringUtils.isEmpty(config.getToken())) {
			throw new YuanpayException("token missing");
		}
		
		String str = MapUrlUtils.getUrlParamsByMap(map);
		str = str + "&" + Md5Utils.cryptHash((config.getToken() + "").trim());
		String verifySign = Md5Utils.cryptHash(str);
		map.put("verifySign", verifySign);
		return map;
	}
	
	
	/*
	 * 请求基础校验
	 */
	protected abstract void dataValidate();
	
	/*
	 * 获得请求链接
	 */
	protected abstract String getAPIUrl(String env);
	
	
	/*
	 * 返回信息转换
	 */
	public abstract T convertResponse(String ret);
}
