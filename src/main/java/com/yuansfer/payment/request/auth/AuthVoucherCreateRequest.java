package com.yuansfer.payment.request.auth;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.auth.AuthVoucherCreateResponse;

public class AuthVoucherCreateRequest extends YuanpayRequest<AuthVoucherCreateResponse> {

	private String outAuthInfoNo;					//商户系统预授权号
	private String outAuthDetailNo;					//商户系统预授权操作号
	private String vendor;
	private String amount;
	private String currency;
	
	public String getOutAuthInfoNo() {
		return outAuthInfoNo;
	}
	public AuthVoucherCreateRequest setOutAuthInfoNo(String outAuthInfoNo) {
		this.outAuthInfoNo = outAuthInfoNo;
		return this;
	}
	public String getOutAuthDetailNo() {
		return outAuthDetailNo;
	}
	public AuthVoucherCreateRequest setOutAuthDetailNo(String outAuthDetailNo) {
		this.outAuthDetailNo = outAuthDetailNo;
		return this;
	}
	public String getVendor() {
		return vendor;
	}
	public AuthVoucherCreateRequest setVendor(String vendor) {
		this.vendor = vendor;
		return this;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public AuthVoucherCreateRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	
	
	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.outAuthInfoNo)) {
			throw new YuanpayException("outAuthInfoNo missing");
		}
		if (StringUtils.isEmpty(this.outAuthDetailNo)) {
			throw new YuanpayException("outAuthDetailNo missing");
		}
		if (StringUtils.isEmpty(this.vendor)) {
			throw new YuanpayException("vendor missing");
		}
		ParamValidator.amountValidate("amount", this.amount);
	}
	@Override
	protected String getAPIUrl(String env) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AuthVoucherCreateResponse convertResponse(String ret) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
