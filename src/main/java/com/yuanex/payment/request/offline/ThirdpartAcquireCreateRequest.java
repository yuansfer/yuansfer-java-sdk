package com.yuanex.payment.request.offline;

import org.apache.commons.lang.StringUtils;

import com.yuanex.payment.enums.CurrencyEnums;
import com.yuanex.payment.enums.VendorEnums;
import com.yuanex.payment.request.ParamValidator;
import com.yuanex.payment.request.RequestConstants;
import com.yuanex.payment.request.YuanpayRequest;
import com.yuanex.payment.response.offline.ThirdpartAcquireCreateResponse;

import net.sf.json.JSONObject;

public class ThirdpartAcquireCreateRequest extends YuanpayRequest<ThirdpartAcquireCreateResponse> {

	
	private String amount;						//美金金额
	private String rmbAmount;					//人民币金额
	private String reference;					//商户支付流水号
	private String currency;					//币种
	private String vendor;						//渠道
	private String description;					//订单描述，会展示在收银台页面
	private String note;						//备注信息，会原样返回
	private Integer timeout;					//过期时间
	private String ipnUrl;						//异步回调地址
	private String alipayUserId;				//支付宝userid

	public String getAmount() {
		return amount;
	}
	public ThirdpartAcquireCreateRequest setAmount(String amount) {
		this.amount = amount;
		return this;
	}
	public String getRmbAmount() {
		return rmbAmount;
	}
	public ThirdpartAcquireCreateRequest setRmbAmount(String rmbAmount) {
		this.rmbAmount = rmbAmount;
		return this;
	}
	public String getReference() {
		return reference;
	}
	public ThirdpartAcquireCreateRequest setReference(String reference) {
		this.reference = reference;
		return this;
	}
	public String getCurrency() {
		return currency;
	}
	public ThirdpartAcquireCreateRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	public String getVendor() {
		return vendor;
	}
	public ThirdpartAcquireCreateRequest setVendor(String vendor) {
		this.vendor = vendor;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public ThirdpartAcquireCreateRequest setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getNote() {
		return note;
	}
	public ThirdpartAcquireCreateRequest setNote(String note) {
		this.note = note;
		return this;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public ThirdpartAcquireCreateRequest setTimeout(Integer timeout) {
		this.timeout = timeout;
		return this;
	}
	public String getIpnUrl() {
		return ipnUrl;
	}
	public ThirdpartAcquireCreateRequest setIpnUrl(String ipnUrl) {
		this.ipnUrl = ipnUrl;
		return this;
	}
	public String getAlipayUserId() {
		return alipayUserId;
	}
	public ThirdpartAcquireCreateRequest setAlipayUserId(String alipayUserId) {
		this.alipayUserId = alipayUserId;
		return this;
	}
	
	@Override
	protected void dataValidate() {
		//金额校验
		if (StringUtils.isEmpty(this.amount) && StringUtils.isEmpty(this.rmbAmount)) {
			throw new RuntimeException("amount and rmbAmount cannnot be null at the same time.");
		} else if (StringUtils.isNotEmpty(this.amount) && StringUtils.isNotEmpty(this.rmbAmount)) {
			throw new RuntimeException("amount and rmbAmount can't exist at the same time.");
		} else if (StringUtils.isNotEmpty(this.amount)) {
			ParamValidator.amountValidate("amount", this.amount);
		} else {
			ParamValidator.amountValidate("rmbAmount", this.rmbAmount);
		}
		
		
		if (StringUtils.isEmpty(this.reference)) {
			throw new RuntimeException("reference missing");
		}
		
		//币种校验
		if (StringUtils.isEmpty(this.currency)) {
			throw new RuntimeException("currency missing");
		}
		if (!CurrencyEnums.USD.getValue().equals(this.currency)) {
			throw new RuntimeException("only USD is supported yet.");
		}
		
		//vendor校验
		if (StringUtils.isEmpty(this.vendor)) {
			throw new RuntimeException("vendor missing");
		}
		boolean vendorFlag = VendorEnums.containValidate(this.vendor);
		if (!vendorFlag) {
			throw new RuntimeException("data error: vendor.");
		}
		
		//description,note校验
		if (StringUtils.isNotEmpty(this.description)) {
			if (this.description.length() > 100) {
				throw new RuntimeException("description is too big.");
			}
		}
		if (StringUtils.isNotEmpty(this.note)) {
			if (this.note.length() > 100) {
				throw new RuntimeException("note is too big.");
			}
		}
		
		if (StringUtils.isEmpty(this.alipayUserId)) {
			throw new RuntimeException("alipayUserId missing");
		}
		
	}
	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.THIRDPART_ACQUIRE_CREATE;
		return url;
	}
	
	
	@Override
	public ThirdpartAcquireCreateResponse convertResponse(String ret) {
		ThirdpartAcquireCreateResponse response = new ThirdpartAcquireCreateResponse();
		JSONObject json = JSONObject.fromObject(ret);
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		
		if (null != json.get("result")) {
			JSONObject result = json.getJSONObject("result");
			response.setResult(result);
		}
		return response;
	}
	
	
}
