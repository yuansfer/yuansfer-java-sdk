package com.yuansfer.payment.request.mobile;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.mobile.MobilePrepayResponse;

import net.sf.json.JSONObject;

public class MobilePrepayRequest extends YuanpayRequest<MobilePrepayResponse> {

	private String reference;						//商户支付流水号
	private String amount;							//美金金额
	private String currency;						//币种
	private String settleCurrency;	
	private String vendor;							//渠道
	private String terminal;						//客户端类型，包括"MINIPROGRAM","APP" 
	private String ipnUrl;							//异步回调地址
	private String openid;							//微信的openid
	private String description;						//订单描述
	private String note;							//备注
	
	public String getReference() {
		return reference;
	}

	public MobilePrepayRequest setReference(String reference) {
		this.reference = reference;
		return this;
	}

	public String getAmount() {
		return amount;
	}

	public MobilePrepayRequest setAmount(String amount) {
		this.amount = amount;
		return this;
	}


	public String getCurrency() {
		return currency;
	}

	public MobilePrepayRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public String getVendor() {
		return vendor;
	}

	public MobilePrepayRequest setVendor(String vendor) {
		this.vendor = vendor;
		return this;
	}
	
	public String getTerminal() {
		return terminal;
	}

	public MobilePrepayRequest setTerminal(String terminal) {
		this.terminal = terminal;
		return this;
	}

	public String getIpnUrl() {
		return ipnUrl;
	}

	public MobilePrepayRequest setIpnUrl(String ipnUrl) {
		this.ipnUrl = ipnUrl;
		return this;
	}

	public String getOpenid() {
		return openid;
	}

	public MobilePrepayRequest setOpenid(String openid) {
		this.openid = openid;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public MobilePrepayRequest setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getNote() {
		return note;
	}

	public MobilePrepayRequest setNote(String note) {
		this.note = note;
		return this;
	}
	
	public String getSettleCurrency() {
		return settleCurrency;
	}

	public MobilePrepayRequest setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
		return this;
	}


	@Override
	protected void dataValidate() {
		//金额校验
		if (StringUtils.isEmpty(this.amount)) {
			throw new YuanpayException("amount missing");
		}  
		
		if (StringUtils.isEmpty(this.reference)) {
			throw new YuanpayException("reference missing");
		}
		
		//币种校验
		if (StringUtils.isEmpty(this.currency)) {
			throw new YuanpayException("currency missing");
		}
		
		if (StringUtils.isEmpty(this.settleCurrency)) {
			throw new YuanpayException("settleCurrency missing");
		}
		
		//vendor校验
		if (StringUtils.isEmpty(this.vendor)) {
			throw new YuanpayException("vendor missing");
		}
		
		if (StringUtils.isEmpty(this.terminal)) {
			throw new YuanpayException("terminal missing");
		}
		
		//openid校验
		if ("wechatpay".equals(this.vendor)){
			if ("MINIPROGRAM".equals(this.terminal)
					&& StringUtils.isEmpty(this.openid)) {
				throw new YuanpayException("openid missing");
			}
		}
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.MOBILE_PREPAY;
		return url;
	}


	@Override
	public MobilePrepayResponse convertResponse(String ret) {
		MobilePrepayResponse response = new MobilePrepayResponse();
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
