package com.yuanex.payment.response.offline;

import com.yuanex.payment.response.YuanpayResponse;

import net.sf.json.JSONArray;

public class SettleListResponse extends YuanpayResponse {

	private static final long serialVersionUID = 7086373378361680468L;
	private JSONArray settlements;
	private Integer size;
	
	public JSONArray getSettlements() {
		return settlements;
	}
	public void setSettlements(JSONArray settlements) {
		this.settlements = settlements;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
	
}
