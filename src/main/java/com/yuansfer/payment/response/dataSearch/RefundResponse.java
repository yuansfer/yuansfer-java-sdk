package com.yuansfer.payment.response.dataSearch;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class RefundResponse extends YuanpayResponse {

	private static final long serialVersionUID = 6374911535912136018L;
	
	private JSONObject result;

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}
}	
