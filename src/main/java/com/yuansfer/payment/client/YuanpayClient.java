package com.yuanex.payment.client;

import com.yuanex.payment.exception.YuanpayException;
import com.yuanex.payment.request.YuanpayRequest;
import com.yuanex.payment.response.YuanpayResponse;

public interface YuanpayClient {
	
	public <T extends YuanpayResponse> T execute(YuanpayRequest<T> request) throws YuanpayException;
}
