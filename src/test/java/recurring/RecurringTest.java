package recurring;

import com.yuansfer.payment.client.YuanpayClient;
import com.yuansfer.payment.client.YuanpayV300Client;
import com.yuansfer.payment.request.recurring.ApplyTokenRequest;
import com.yuansfer.payment.request.recurring.AutoPayRequest;
import com.yuansfer.payment.request.recurring.ConsultRequest;
import com.yuansfer.payment.request.recurring.RevokeRequest;
import com.yuansfer.payment.response.recurring.ApplyTokenResponse;
import com.yuansfer.payment.response.recurring.AutoPayResponse;
import com.yuansfer.payment.response.recurring.ConsultResponse;
import com.yuansfer.payment.response.recurring.RevokeResponse;

import common.InitYuanpayConfig;
import net.sf.json.JSONObject;

public class RecurringTest {

	public static void main(String[] args) {
		YuanpayClient client = new YuanpayV300Client(InitYuanpayConfig.initMerchantConfig());
		
//		consult(client);
		
//		applyToken(client);
		
		autoPay(client);
		
//		revoke(client);
	}
	
	public static void consult(YuanpayClient client) {
		
		ConsultRequest request = new ConsultRequest();
		request.setOsType("IOS")
				.setOsVersion("14.0.1")
				.setAutoIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
				.setAutoRedirectUrl("http://zk-tys.yunkeguan.com/tteest/test2")
				.setTerminal("ONLINE")
				.setVendor("gcash")
				.setAutoReference(System.nanoTime()+"");
		
		ConsultResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	public static void applyToken(YuanpayClient client) {
		ApplyTokenRequest request = new ApplyTokenRequest();
		
		request.setAutoDebitNo("391807487291286634")
				.setGrantType("AUTHORIZATION_CODE");
		
		ApplyTokenResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	public static void autoPay(YuanpayClient client) {
		AutoPayRequest request = new AutoPayRequest();
		
		request.setAmount("1")
				.setAutoDebitNo("391807487291286634")
				.setCurrency("PHP")
				.setSettleCurrency("USD")
				.setIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
				.setReference(System.nanoTime()+"");
		
		AutoPayResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	public static void revoke(YuanpayClient client) {
		RevokeRequest request = new RevokeRequest();
		
		request.setAutoDebitNo("391807487291286634");
		
		RevokeResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
}
