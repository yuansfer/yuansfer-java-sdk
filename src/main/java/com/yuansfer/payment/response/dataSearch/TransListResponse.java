package com.yuansfer.payment.response.dataSearch;

import com.yuansfer.payment.response.YuanpayResponse;

import net.sf.json.JSONArray;

public class TransListResponse extends YuanpayResponse {

	private static final long serialVersionUID = -7177530634871656014L;
	private JSONArray transactions;
	private Integer size;
	public JSONArray getTransactions() {
		return transactions;
	}
	public void setTransactions(JSONArray transactions) {
		this.transactions = transactions;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
}
