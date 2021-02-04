package auth;

import com.yuansfer.payment.client.YuanpayClient;
import com.yuansfer.payment.client.YuanpayV300Client;
import com.yuansfer.payment.request.auth.AuthCaptureRequest;
import com.yuansfer.payment.request.auth.AuthFreezeRequest;
import com.yuansfer.payment.request.auth.AuthUnfreezeRequest;
import com.yuansfer.payment.request.auth.AuthVoucherCreateRequest;
import com.yuansfer.payment.response.auth.AuthCaptureResponse;
import com.yuansfer.payment.response.auth.AuthFreezeResponse;
import com.yuansfer.payment.response.auth.AuthUnfreezeResponse;
import com.yuansfer.payment.response.auth.AuthVoucherCreateResponse;

import common.InitYuanpayConfig;
import net.sf.json.JSONObject;

public class AuthTest {

	public static void main(String[] args) {
		YuanpayClient client = new YuanpayV300Client(InitYuanpayConfig.initMerchantConfig());
	
//		freeze(client);
		
//		voucherCreate(client);
		
//		unfreeze(client);
		
		capture(client);
	}
	
	public static void freeze(YuanpayClient client) {
		AuthFreezeRequest request = new AuthFreezeRequest();
		
		request.setAmount("1")
				.setAuthIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
				.setCurrency("USD")
				.setNote("testNote")
				.setOutAuthDetailNo("outAuthDetailNo" + System.nanoTime())
				.setOutAuthInfoNo("outAuthInfoNo" + System.nanoTime())
				.setSettleCurrency("USD")
				.setVendor("alipay")
				.setPaymentBarcode("285806388073880943");
		
		AuthFreezeResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
		
	}
	
	
	public static void voucherCreate(YuanpayClient client) {
		AuthVoucherCreateRequest request = new AuthVoucherCreateRequest();
		request.setAmount("1")
				.setCurrency("USD")
				.setOutAuthDetailNo("outAuthDetailNo" + System.nanoTime())
				.setOutAuthInfoNo("outAuthInfoNo" + System.nanoTime())
				.setSettleCurrency("USD")
				.setVendor("alipay");
		
		AuthVoucherCreateResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	
	public static void unfreeze(YuanpayClient client) {
		AuthUnfreezeRequest request = new AuthUnfreezeRequest();
		request.setOutAuthDetailNo("outAuthDetailNo" + System.nanoTime())
		.setAuthIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
		.setCurrency("USD")
		.setOutAuthInfoNo("outAuthInfoNo97573448246545")
		.setSettleCurrency("USD")
		.setUnfreezeAmount("1");
		
		AuthUnfreezeResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	
	public static void capture(YuanpayClient client) {
		AuthCaptureRequest request = new AuthCaptureRequest();
		request.setAmount("1")
				.setCurrency("USD")
				.setIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
				.setOutAuthDetailNo("outAuthDetailNo"+System.nanoTime())
				.setOutAuthInfoNo("outAuthInfoNo97797816249269")
				.setSettleCurrency("USD")
				.setReference("reference"+System.nanoTime());
		
		AuthCaptureResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
}
