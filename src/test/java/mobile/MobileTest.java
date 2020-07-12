package mobile;

import com.yuansfer.payment.client.YuanpayClient;
import com.yuansfer.payment.client.YuanpayV200Client;
import com.yuansfer.payment.request.mobile.ExpressPayRequest;
import com.yuansfer.payment.request.mobile.MobilePrepayRequest;
import com.yuansfer.payment.response.mobile.ExpressPayResponse;
import com.yuansfer.payment.response.mobile.MobilePrepayResponse;

import common.InitYuanpayConfig;
import net.sf.json.JSONObject;

public class MobileTest {

	
	public static void main(String[] args) {
		prepay();
		
//		expresspay();
	}
	
	
	public static void expresspay() {
		YuanpayClient client = new YuanpayV200Client(InitYuanpayConfig.initMerchantConfig());
		
		ExpressPayRequest request = new ExpressPayRequest();
		request.setAmount("0.01")
				.setCardCvv("248")
				.setCardExpMonth("12")
				.setCardExpYear("2025")
				.setCardNumber("6226388000000095")
				.setClientIp("127.0.0.1")
				.setCurrency("USD")
				.setReference(System.nanoTime()+"");
		
		ExpressPayResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	
	public static void prepay() {
		YuanpayClient client = new YuanpayV200Client(InitYuanpayConfig.initMerchantConfig());
		
		
		/*
		 * Miniprogram of Wechat
		 */
//		MobilePrepayRequest request = new MobilePrepayRequest();
//		request.setAmount("0.01")
//				.setCurrency("USD")
//				.setDescription("testDescription")
//				.setIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
//				.setNote("testNote")
//				.setOpenid("ocBgh5fnabrf-pxPgCWXlq2mOvG8")
//				.setReference(System.nanoTime()+"")
//				.setTerminal("MINIPROGRAM")
//				.setVendor("wechatpay");
		
		
		/*
		 * APP of Wechat
		 */
//		MobilePrepayRequest request = new MobilePrepayRequest();
//		request.setAmount("0.01")
//				.setCurrency("USD")
//				.setDescription("testDescription")
//				.setIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
//				.setNote("testNote")
//				.setReference(System.nanoTime()+"")
//				.setTerminal("APP")
//				.setVendor("wechatpay");
		
		/*
		 * APP of Alipay
		 */
		MobilePrepayRequest request = new MobilePrepayRequest();
		request.setAmount("0.01")
				.setCurrency("USD")
				.setDescription("testDescription")
				.setIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
				.setNote("testNote")
				.setReference(System.nanoTime()+"")
				.setTerminal("APP")
				.setVendor("alipay");
		
		MobilePrepayResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
}
