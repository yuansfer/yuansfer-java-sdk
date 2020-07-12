package offline;

import com.yuanex.payment.client.YuanpayClient;
import com.yuanex.payment.client.YuanpayV200Client;
import com.yuanex.payment.config.YuanpayConfig;
import com.yuanex.payment.request.offline.InstoreAddRequest;
import com.yuanex.payment.request.offline.InstoreCashierAddRequest;
import com.yuanex.payment.request.offline.InstoreCreateTranQrcodeRequest;
import com.yuanex.payment.request.offline.InstorePayRequest;
import com.yuanex.payment.response.offline.InstoreAddResponse;
import com.yuanex.payment.response.offline.InstoreCashierAddResponse;
import com.yuanex.payment.response.offline.InstoreCreateTranQrcodeResponse;
import com.yuanex.payment.response.offline.InstorePayResponse;

import common.InitYuanpayConfig;
import net.sf.json.JSONObject;

public class OfflineTest {

	public static void main(String[] args) {
		addPay();
		
//		tranQrcode();
		
//		cashierAdd();
	}
	
	
	
	public static void cashierAdd() {
		YuanpayConfig config = InitYuanpayConfig.initMerchantConfig();
		config.setStoreAdminNo("3000140001");
		YuanpayClient client = new YuanpayV200Client(config);
		
		InstoreCashierAddRequest request = new InstoreCashierAddRequest();

		request.setAmount("0.01")
				.setCurrency("USD")
				.setIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
				.setReference(System.nanoTime()+"");
		
		InstoreCashierAddResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	
	public static void tranQrcode() {
		YuanpayClient client = new YuanpayV200Client(InitYuanpayConfig.initMerchantConfig());
		
		InstoreCreateTranQrcodeRequest request = new InstoreCreateTranQrcodeRequest();
		
		request.setAmount("0.01")
				.setCurrency("USD")
				.setIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
				.setNeedQrcode("true")
				.setReference(System.nanoTime()+"")
				.setTimeout(120)
				.setVendor("alipay");
		
		InstoreCreateTranQrcodeResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	
	public static void addPay() {
		YuanpayClient client = new YuanpayV200Client(InitYuanpayConfig.initMerchantConfig());
		
		//add，pay流程
		InstoreAddRequest addRequest = new InstoreAddRequest();
		addRequest.setAmount("0.01")
					.setCurrency("USD")
					.setReference(System.nanoTime()+"");
		
		InstoreAddResponse addResponse = client.execute(addRequest);
		System.out.println("add response:" + JSONObject.fromObject(addResponse));
		
		String transactionNo = addResponse.getTransaction().getString("transactionNo");
		
		InstorePayRequest payRequest = new InstorePayRequest();
		payRequest.setTransactionNo(transactionNo)
					.setVendor("alipay")
					.setPaymentBarcode("281036155210403960");
		
		InstorePayResponse payResponse = client.execute(payRequest);
		System.out.println("pay response:" + JSONObject.fromObject(payResponse));
		
	}
}
