package com.yuanex.payment.response.offline;

import com.yuanex.payment.response.YuanpayResponse;

import net.sf.json.JSONObject;

public class InstoreAddResponse extends YuanpayResponse {

	private static final long serialVersionUID = 2458591733625822127L;
	
	private JSONObject transaction;

	public JSONObject getTransaction() {
		return transaction;
	}

	public void setTransaction(JSONObject transaction) {
		this.transaction = transaction;
	}


}
