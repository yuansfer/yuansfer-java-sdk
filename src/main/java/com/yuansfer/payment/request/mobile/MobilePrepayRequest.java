package com.yuansfer.payment.request.mobile;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.enums.CurrencyEnums;
import com.yuansfer.payment.enums.TerminalEnums;
import com.yuansfer.payment.enums.VendorEnums;
import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.mobile.MobilePrepayResponse;

import net.sf.json.JSONObject;

public class MobilePrepayRequest extends YuanpayRequest<MobilePrepayResponse> {

	private String reference;						//商户支付流水号
	private String amount;							//美金金额
	private String rmbAmount;						//人民币金额
	private String currency;						//币种
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

	public String getRmbAmount() {
		return rmbAmount;
	}

	public MobilePrepayRequest setRmbAmount(String rmbAmount) {
		this.rmbAmount = rmbAmount;
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


	@Override
	protected void dataValidate() {
		//金额校验
		if (StringUtils.isEmpty(this.amount) && StringUtils.isEmpty(this.rmbAmount)) {
			throw new YuanpayException("amount and rmbAmount cannnot be null at the same time.");
		} else if (StringUtils.isNotEmpty(this.amount) && StringUtils.isNotEmpty(this.rmbAmount)) {
			throw new YuanpayException("amount and rmbAmount can't exist at the same time.");
		} else if (StringUtils.isNotEmpty(this.amount)) {
			
			ParamValidator.amountValidate("amount", this.amount);
		} else {
			ParamValidator.amountValidate("rmbAmount", this.rmbAmount);
		}
		
		
		if (StringUtils.isEmpty(this.reference)) {
			throw new YuanpayException("reference missing");
		}
		
		//币种校验
		if (StringUtils.isEmpty(this.currency)) {
			throw new YuanpayException("currency missing.");
		}
		if (!CurrencyEnums.USD.getValue().equals(this.currency)) {
			throw new YuanpayException("only USD is supported yet.");
		}
		
		//vendor校验
		if (StringUtils.isEmpty(this.vendor)) {
			throw new YuanpayException("vendor missing.");
		}
		boolean vendorFlag = VendorEnums.containValidate(this.vendor);
		if (!vendorFlag) {
			throw new YuanpayException("data error: vendor.");
		}
		
		if (StringUtils.isEmpty(this.terminal)) {
			throw new YuanpayException("terminal missing");
		}
		
		//openid校验
		if (VendorEnums.WECHATPAY.getValue().equals(this.vendor)){
			if (TerminalEnums.MINIPROGRAM.getValue().equals(this.terminal)
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
