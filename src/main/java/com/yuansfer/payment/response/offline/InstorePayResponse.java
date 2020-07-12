package com.yuanex.payment.response.offline;

import com.yuanex.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class InstorePayResponse extends YuanpayResponse {

	private static final long serialVersionUID = 5387576527153647388L;
	
	private JSONObject transaction;

	public JSONObject getTransaction() {
		return transaction;
	}

	public void setTransaction(JSONObject transaction) {
		this.transaction = transaction;
	}
	
	
}
