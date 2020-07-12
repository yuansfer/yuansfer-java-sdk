package com.yuansfer.payment.request.offline;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.enums.CurrencyEnums;
import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.offline.InstoreCreateTranQrcodeResponse;

import net.sf.json.JSONObject;

public class InstoreCreateTranQrcodeRequest extends YuanpayRequest<InstoreCreateTranQrcodeResponse> {

	private String currency;							//币种
	private String vendor;								//渠道
	private String reference;							//商户支付流水号
	private String amount;								//金额
	private String ipnUrl;								//异步回调地址
	private String needQrcode;							//是否需要生成二维码图片，默认为true
	private Integer timeout;							//超市时间
	private @Deprecated String preAuth;					//预付款标志  true是， false则为普通订单, 默认为false
	
	public String getCurrency() {
		return currency;
	}

	public InstoreCreateTranQrcodeRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public String getVendor() {
		return vendor;
	}

	public InstoreCreateTranQrcodeRequest setVendor(String vendor) {
		this.vendor = vendor;
		return this;
	}

	public String getReference() {
		return reference;
	}

	public InstoreCreateTranQrcodeRequest setReference(String reference) {
		this.reference = reference;
		return this;
	}

	public String getAmount() {
		return amount;
	}

	public InstoreCreateTranQrcodeRequest setAmount(String amount) {
		this.amount = amount;
		return this;
	}

	public String getIpnUrl() {
		return ipnUrl;
	}

	public InstoreCreateTranQrcodeRequest setIpnUrl(String ipnUrl) {
		this.ipnUrl = ipnUrl;
		return this;
	}

	public String getNeedQrcode() {
		return needQrcode;
	}

	public InstoreCreateTranQrcodeRequest setNeedQrcode(String needQrcode) {
		this.needQrcode = needQrcode;
		return this;
	}

	public String getPreAuth() {
		return preAuth;
	}

	public InstoreCreateTranQrcodeRequest setPreAuth(String preAuth) {
		this.preAuth = preAuth;
		return this;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public InstoreCreateTranQrcodeRequest setTimeout(Integer timeout) {
		this.timeout = timeout;
		return this;
	}
	

	@Override
	protected void dataValidate() {
		ParamValidator.amountValidate("amount", this.amount);
		
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
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.INSTORE_TRAN_QRCODE;
		return url;
	}


	@Override
	public InstoreCreateTranQrcodeResponse convertResponse(String ret) {
		InstoreCreateTranQrcodeResponse response = new InstoreCreateTranQrcodeResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("ret_code")) {
			response.setRetCode(json.getString("ret_code"));
		}
		if (null != json.get("ret_msg")) {
			response.setRetMsg(json.getString("ret_msg"));
		}
		if (null != json.get("transactionNo")) {
			response.setTransactionNo(json.getString("transactionNo"));
		}
		if (null != json.get("reference")) {
			response.setReference(json.getString("reference"));
		}
		if (null != json.get("amount")) {
			response.setAmount(json.getString("amount"));
		}
		if (null != json.get("timeout")) {
			response.setTimeout(json.getInt("timeout"));
		}
		if (null != json.get("deepLink")) {
			response.setDeepLink(json.getString("deepLink"));
		}
		if (null != json.get("qrcodeUrl")) {
			response.setQrcodeUrl(json.getString("qrcodeUrl"));
		}
		return response;
	}

}
