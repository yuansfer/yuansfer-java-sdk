package com.yuanex.payment.response.dataSearch;

import com.yuanex.payment.response.YuanpayResponse;

import net.sf.json.JSONArray;

public class WithdrawalListResponse extends YuanpayResponse {

	private static final long serialVersionUID = 9088571348625589951L;
	private JSONArray withdrawals;
	private Integer size;
	
	public JSONArray getWithdrawals() {
		return withdrawals;
	}
	public void setWithdrawals(JSONArray withdrawals) {
		this.withdrawals = withdrawals;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
}
