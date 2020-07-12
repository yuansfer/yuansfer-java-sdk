package com.yuansfer.payment.response.dataSearch;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class ReverseResponse extends YuanpayResponse {

	private static final long serialVersionUID = 4617089503725402504L;

	private JSONObject transaction;

	public JSONObject getTransaction() {
		return transaction;
	}

	public void setTransaction(JSONObject transaction) {
		this.transaction = transaction;
	}

}
