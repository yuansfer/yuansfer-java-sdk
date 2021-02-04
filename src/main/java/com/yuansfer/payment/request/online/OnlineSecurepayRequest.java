package com.yuansfer.payment.request.online;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.exception.YuanpayException;
import com.yuansfer.payment.request.RequestConstants;
import com.yuansfer.payment.request.YuanpayRequest;
import com.yuansfer.payment.response.online.OnlineSecurepayResponse;
import com.yuansfer.payment.utils.JSONUtils;

import net.sf.json.JSONObject;

/**
 * @author yuansfer
 */
public class OnlineSecurepayRequest extends YuanpayRequest<OnlineSecurepayResponse> {
	 
	private String amount;						//美金金额
	private String currency;					//币种
	private String settleCurrency;				//结算币种
	private String reference;					//商户支付流水号
	private String vendor;						//渠道
	private String terminal;					//客户端类型 包括 ONLINE，WAP
	private Integer timeout;					//过期时间
	private String ipnUrl;						//异步回调地址
	private String callbackUrl;					//同步回调地址
	private String goodsInfo;					//商品信息，要求json格式
	
	private String description;					//订单描述，会展示在收银台页面
	private String note;						//备注信息，会原样返回
	private String osType;
	//信用卡相关
	private String creditType;
	private Integer paymentCount;
	private Integer frequency;
	
	
	public String getAmount() {
		return amount;
	}
	public OnlineSecurepayRequest setAmount(String amount) {
		this.amount = amount;
		return this;
	}
	public String getCurrency() {
		return currency;
	}
	public OnlineSecurepayRequest setCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	public String getVendor() {
		return vendor;
	}
	public OnlineSecurepayRequest setVendor(String vendor) {
		this.vendor = vendor;
		return this;
	}
	public String getIpnUrl() {
		return ipnUrl;
	}
	public OnlineSecurepayRequest setIpnUrl(String ipnUrl) {
		this.ipnUrl = ipnUrl;
		return this;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public OnlineSecurepayRequest setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
		return this;
	}
	public String getTerminal() {
		return terminal;
	}
	public OnlineSecurepayRequest setTerminal(String terminal) {
		this.terminal = terminal;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public OnlineSecurepayRequest setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getNote() {
		return note;
	}
	public OnlineSecurepayRequest setNote(String note) {
		this.note = note;
		return this;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public OnlineSecurepayRequest setTimeout(Integer timeout) {
		this.timeout = timeout;
		return this;
	}
	public String getGoodsInfo() {
		return goodsInfo;
	}
	public OnlineSecurepayRequest setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
		return this;
	}
	
	public String getReference() {
		return reference;
	}
	public OnlineSecurepayRequest setReference(String reference) {
		this.reference = reference;
		return this;
	}
	
	public String getCreditType() {
		return creditType;
	}
	public OnlineSecurepayRequest setCreditType(String creditType) {
		this.creditType = creditType;
		return this;
	}
	public Integer getPaymentCount() {
		return paymentCount;
	}
	public OnlineSecurepayRequest setPaymentCount(Integer paymentCount) {
		this.paymentCount = paymentCount;
		return this;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public OnlineSecurepayRequest setFrequency(Integer frequency) {
		this.frequency = frequency;
		return this;
	}
	
	public String getSettleCurrency() {
		return settleCurrency;
	}
	public OnlineSecurepayRequest setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
		return this;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	
	//数据校验
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
			throw new YuanpayException("currency missing.");
		}
		
		if (StringUtils.isEmpty(this.settleCurrency)) {
			throw new YuanpayException("settleCurrency missing");
		}
		
		//vendor校验
		if (StringUtils.isEmpty(this.vendor)) {
			throw new YuanpayException("vendor missing.");
		}
		
		//terminal校验
		if (StringUtils.isEmpty(this.terminal)) {
			throw new YuanpayException("terminal missing");
		}
		
		//description,note校验
		if (StringUtils.isNotEmpty(this.description)) {
			if (this.description.length() > 100) {
				throw new YuanpayException("description is too big");
			}
		}
		if (StringUtils.isNotEmpty(this.note)) {
			if (this.note.length() > 100) {
				throw new YuanpayException("note is too big");
			}
		}
		//goodsInfo校验
		if (StringUtils.isNotEmpty(this.goodsInfo)) {
			boolean jsonFlag = JSONUtils.isStringJsonArrFormat(this.goodsInfo);
			if (!jsonFlag) {
				throw new YuanpayException("goodsInfo should be json array format");
			}
		}
		
	}

	@Override
	protected String getAPIUrl(String env) {
		String urlPrefix = getUrlPrefix(env);
		String url = urlPrefix + RequestConstants.ONLINE_SECUREPAY;
		return url;
	}
	
	
	
	@Override
	public OnlineSecurepayResponse convertResponse(String ret) {
		OnlineSecurepayResponse response = new OnlineSecurepayResponse();
		JSONObject json = JSONObject.fromObject(ret);
		if (null != json.get("result")) {
			response.setResult(json.getJSONObject("result"));
		}
		response.setRetCode(json.getString("ret_code"));
		response.setRetMsg(json.getString("ret_msg"));
		return response;
	}
	
}
