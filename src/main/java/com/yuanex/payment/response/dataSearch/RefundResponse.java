package com.yuanex.payment.response.dataSearch;

import com.yuanex.payment.response.YuanpayResponse;

public class RefundResponse extends YuanpayResponse {

	private static final long serialVersionUID = 6374911535912136018L;
	
	private RefundBody result;

	public RefundBody getResult() {
		return result;
	}

	public void setResult(RefundBody result) {
		this.result = result;
	}
}	
