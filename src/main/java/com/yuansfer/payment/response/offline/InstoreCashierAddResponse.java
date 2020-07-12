package com.yuansfer.payment.response.offline;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class InstoreCashierAddResponse extends YuanpayResponse {

	private static final long serialVersionUID = -7067527616659878204L;
	private JSONObject transaction;

	public JSONObject getTransaction() {
		return transaction;
	}

	public void setTransaction(JSONObject transaction) {
		this.transaction = transaction;
	}
	
}
