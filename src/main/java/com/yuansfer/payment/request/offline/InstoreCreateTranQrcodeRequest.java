package com.yuansfer.payment.request.offline;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.offline.InstoreCreateTranQrcodeResponse;

import net.sf.json.JSONObject;

public class InstoreCreateTranQrcodeRequest extends YuanpayRequest<InstoreCreateTranQrcodeResponse> {

	private String amount;								//金额
	private String currency;							//币种
	private String settleCurrency;
	private String vendor;								//渠道
	private String reference;							//商户支付流水号
	private String ipnUrl;								//异步回调地址
	private String needQrcode;							//是否需要生成二维码图片，默认为true
	private Integer timeout;							//超市时间
	
	public String getCurrency() {
		return currency;
	}

	public InstoreCreateTranQrcodeRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	
	public String getSettleCurrency() {
		return settleCurrency;
	}

	public InstoreCreateTranQrcodeRequest setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
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
		if (StringUtils.isEmpty(this.settleCurrency)) {
			throw new YuanpayException("settleCurrency missing.");
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
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}

	
}
