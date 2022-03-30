package com.yuansfer.payment.request.dataSearch;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.dataSearch.TranQueryResponse;

import net.sf.json.JSONObject;

public class TranQueryRequest extends YuanpayRequest<TranQueryResponse> {
	
	private String transactionNo;						//订单号
	private String reference;							//商户支付流水号
	
	public String getTransactionNo() {
		return transactionNo;
	}

	public TranQueryRequest setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
		return this;
	}

	public String getReference() {
		return reference;
	}

	public TranQueryRequest setReference(String reference) {
		this.reference = reference;
		return this;
	}


	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.transactionNo) && StringUtils.isEmpty(this.reference)) {
			throw new RuntimeException("transaction and reference cannot be null at the same time");
		}
		if (StringUtils.isNotEmpty(this.transactionNo) && StringUtils.isNotEmpty(this.reference)) {
			throw new RuntimeException("transaction and reference cannot exist at the same time");
		}
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.TRAN_QUERY;
		return url;
	}

	@Override
	public TranQueryResponse convertResponse(String ret) {
		TranQueryResponse response = new TranQueryResponse();
		JSONObject json = JSONObject.fromObject(ret);
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		return response;
	}
}
