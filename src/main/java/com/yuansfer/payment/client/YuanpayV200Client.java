package com.yuansfer.payment.client;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.yuansfer.payment.config.YuanpayConfig;
import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestBody;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.YuanpayResponse;
import com.yuansfer.payment.utils.HTTPSSecureProtocolSocketFactory;

import net.sf.json.JSONObject;

/**
 * 
 * @author zhoukai
 * yuanpay客户端
 * 
 * @version 2.0.0
 */
public class YuanpayV200Client implements YuanpayClient {
	private static final Logger logger = Logger.getLogger(YuanpayV200Client.class);
	private YuanpayConfig yuanpayConfig;
	
	public YuanpayV200Client(YuanpayConfig yuanpayConfig) {
		this.yuanpayConfig = yuanpayConfig;
	}
	
	public static HttpClient getDeafultClient() {
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
		client.getHttpConnectionManager().getParams().setSoTimeout(30000);
		return client;
	}

	@Override
	public <T extends YuanpayResponse> T execute(YuanpayRequest<T> request) {
		try {
			HttpClient client = getDeafultClient();
	        Protocol https = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 443);
	        Protocol.registerProtocol("https", https);
	        
	        //关联配置
	        if (null == yuanpayConfig) {
	        	logger.error("missing config infomation.");
	        	throw new YuanpayException("missiong config infomation.");
	        } else {
	        	//基础权限数据校验
	        	yuanpayConfig.basicValidate();
	        }

	        RequestBody requestBody = request.initRequestBody(yuanpayConfig);
	        String url = requestBody.getUrl();
			Map<String, String> params = requestBody.getParams();
			
	        //业务数据校验
			PostMethod postMethod = new PostMethod(url);
			
			JSONObject jsonParams = null;
			if (MapUtils.isNotEmpty(params)) {
				jsonParams = JSONObject.fromObject(params);
			} else {
				jsonParams = new JSONObject();
			}
			String transJson = jsonParams.toString();
			RequestEntity se = new StringRequestEntity(transJson, "application/json", "UTF-8");
			postMethod.setRequestEntity(se);
			client.executeMethod(postMethod);
			
			String responseBody = postMethod.getResponseBodyAsString();
			logger.info("yuansfer return:" + responseBody);
			if (StringUtils.isEmpty(responseBody)) {
				logger.error("yuansfer return null");
				throw new YuanpayException("fail to connect");
			}
			//解析返回
			T t = request.convertResponse(responseBody);
			return t;
		} catch (Exception e) {
			throw new YuanpayException(e.getMessage());
		} finally {
			Protocol.unregisterProtocol("https");
		}
	}
	
	
}
