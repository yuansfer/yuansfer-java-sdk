package com.yuansfer.payment.client;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuansfer.payment.config.YuanpayConfig;
import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestBody;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.YuanpayResponse;
import com.yuansfer.payment.utils.OkHttpUtils;

import net.sf.json.JSONObject;

/**
 * 
 * @author yuansfer
 * yuanpay客户端
 * 
 * @version 2.0.0
 */
public class YuanpayV300Client implements YuanpayClient {
	private static final Logger logger = LoggerFactory.getLogger(YuanpayV300Client.class);
	private YuanpayConfig yuanpayConfig;
	
	public YuanpayV300Client(YuanpayConfig yuanpayConfig) {
		this.yuanpayConfig = yuanpayConfig;
	}
	

	@Override
	public <T extends YuanpayResponse> T execute(YuanpayRequest<T> request) {
		try {
	        //关联配置
	        if (null == yuanpayConfig) {
	        	logger.error("missing config information.");
	        	throw new YuanpayException("missing config information.");
	        } else {
	        	//基础权限数据校验
	        	yuanpayConfig.basicValidate();
	        }

	        RequestBody requestBody = request.initRequestBody(yuanpayConfig);
	        String url = requestBody.getUrl();
			Map<String, String> params = requestBody.getParams();
			
	        //业务数据校验
			JSONObject jsonParams;
			if (MapUtils.isNotEmpty(params)) {
				jsonParams = JSONObject.fromObject(params);
			} else {
				jsonParams = new JSONObject();
			}
			String transJson = jsonParams.toString();
			String responseBody = OkHttpUtils.doJsonPost(url, transJson);
			logger.info("yuansfer return:" + responseBody);
			if (StringUtils.isEmpty(responseBody)) {
				logger.error("yuansfer return null");
				throw new YuanpayException("fail to connect");
			}
			//解析返回
			return request.convertResponse(responseBody);
		} catch (Exception e) {
			throw new YuanpayException(e.getMessage());
		} finally {
		}
	}
	
	
}
