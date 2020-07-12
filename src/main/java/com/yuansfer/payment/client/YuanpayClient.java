package com.yuansfer.payment.client;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.YuanpayResponse;

public interface YuanpayClient {
	
	public <T extends YuanpayResponse> T execute(YuanpayRequest<T> request) throws YuanpayException;
}
