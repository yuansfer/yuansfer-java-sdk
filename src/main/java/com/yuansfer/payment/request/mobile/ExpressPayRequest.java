package com.yuansfer.payment.request.mobile;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.ParamValidator;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.mobile.ExpressPayResponse;

import net.sf.json.JSONObject;

public class ExpressPayRequest extends YuanpayRequest<ExpressPayResponse> {
	
	private String cardNumber;						//卡号
	private String cardExpYear;						//卡过期年限
	private String cardExpMonth;					//卡过期月份
	private String cardCvv;							//卡cvv码
	private String amount;							//金额
	private String reference;						//商户系统流水号
	private String clientIp;						//商户系统ip
	private String currency;						//币种
	private String settleCurrency;
	
	
	public String getCardNumber() {
		return cardNumber;
	}

	public ExpressPayRequest setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
		return this;
	}

	public String getCardExpYear() {
		return cardExpYear;
	}

	public ExpressPayRequest setCardExpYear(String cardExpYear) {
		this.cardExpYear = cardExpYear;
		return this;
	}

	public String getCardExpMonth() {
		return cardExpMonth;
	}

	public ExpressPayRequest setCardExpMonth(String cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
		return this;
	}

	public String getCardCvv() {
		return cardCvv;
	}

	public ExpressPayRequest setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
		return this;
	}

	public String getAmount() {
		return amount;
	}

	public ExpressPayRequest setAmount(String amount) {
		this.amount = amount;
		return this;
	}

	public String getReference() {
		return reference;
	}

	public ExpressPayRequest setReference(String reference) {
		this.reference = reference;
		return this;
	}

	public String getClientIp() {
		return clientIp;
	}

	public ExpressPayRequest setClientIp(String clientIp) {
		this.clientIp = clientIp;
		return this;
	}

	public String getCurrency() {
		return currency;
	}

	public ExpressPayRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}

	public String getSettleCurrency() {
		return settleCurrency;
	}

	public ExpressPayRequest setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
		return this;
	}
	
	@Override
	protected void dataValidate() {
		if (StringUtils.isEmpty(this.cardNumber)) {
			throw new YuanpayException("cardNumber missing");
		}
		if (StringUtils.isEmpty(this.cardExpYear)) {
			throw new YuanpayException("cardExpYear missing");
		}
		if (StringUtils.isEmpty(this.cardExpMonth)) {
			throw new YuanpayException("cardExpMonth missing");
		}
		if (StringUtils.isEmpty(this.cardCvv)) {
			throw new YuanpayException("cardCvv missing");
		}
		
		ParamValidator.numberValidate("cardNumber", this.cardNumber);
		ParamValidator.numberValidate("cardExpYear", this.cardExpYear);
		ParamValidator.numberValidate("cardExpMonth", this.cardExpMonth);
		ParamValidator.numberValidate("cardCvv", this.cardCvv);
		
		ParamValidator.amountValidate("amount", this.amount);
		
		//币种校验
		if (StringUtils.isEmpty(this.currency)) {
			throw new YuanpayException("currency missing.");
		}
		if (StringUtils.isEmpty(this.settleCurrency)) {
			throw new YuanpayException("settleCurrency missing");
		}
		if (StringUtils.isEmpty(this.reference)) {
			throw new YuanpayException("reference missing");
		}
		if (StringUtils.isEmpty(this.clientIp)) {
			throw new YuanpayException("clientIp missing");
		}
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.EXPRESS_PAY;
		return url;
	}

	@Override
	public ExpressPayResponse convertResponse(String ret) {
		ExpressPayResponse response = new ExpressPayResponse();
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
