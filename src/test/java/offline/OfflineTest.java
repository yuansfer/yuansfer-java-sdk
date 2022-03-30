package offline;

import com.yuansfer.payment.client.YuanpayClient;
import com.yuansfer.payment.client.YuanpayV300Client;
import com.yuansfer.payment.config.YuanpayConfig;
import com.yuansfer.payment.request.offline.InstoreAddRequest;
import com.yuansfer.payment.request.offline.InstoreCashierAddRequest;
import com.yuansfer.payment.request.offline.InstoreCreateTranQrcodeRequest;
import com.yuansfer.payment.request.offline.InstorePrepayRequest;
import com.yuansfer.payment.response.offline.InstoreAddResponse;
import com.yuansfer.payment.response.offline.InstoreCashierAddResponse;
import com.yuansfer.payment.response.offline.InstoreCreateTranQrcodeResponse;
import com.yuansfer.payment.response.offline.InstorePrepayResponse;

import common.InitYuanpayConfig;
import net.sf.json.JSONObject;

public class OfflineTest {

	public static void main(String[] args) {
		addPay();
		
//		tranQrcode();
//		
//		cashierAdd();
	}
	
	
	
	public static void cashierAdd() {
		YuanpayConfig config = InitYuanpayConfig.initMerchantConfig();
		config.setStoreAdminNo("3000140001");
		YuanpayClient client = new YuanpayV300Client(config);
		
		InstoreCashierAddRequest request = new InstoreCashierAddRequest();

		request.setAmount("0.01")
				.setCurrency("USD")
				.setSettleCurrency("USD")
				.setIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
				.setReference(System.nanoTime()+"");
		
		InstoreCashierAddResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	
	public static void tranQrcode() {
		YuanpayClient client = new YuanpayV300Client(InitYuanpayConfig.initMerchantConfig());
		
		InstoreCreateTranQrcodeRequest request = new InstoreCreateTranQrcodeRequest();
		
		request.setAmount("0.01")
				.setCurrency("USD")
				.setSettleCurrency("USD")
				.setIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
				.setNeedQrcode("true")
				.setReference(System.nanoTime()+"")
				.setTimeout(120)
				.setVendor("alipay");
		
		InstoreCreateTranQrcodeResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	
	public static void addPay() {
		YuanpayClient client = new YuanpayV300Client(InitYuanpayConfig.initMerchantConfig());
		
		//add，pay流程
		InstoreAddRequest addRequest = new InstoreAddRequest();
		addRequest.setAmount("0.01")
					.setCurrency("USD")
					.setSettleCurrency("USD")
					.setReference(System.nanoTime()+"");
		
		InstoreAddResponse addResponse = client.execute(addRequest);
		System.out.println("add response:" + JSONObject.fromObject(addResponse));
		
		String transactionNo = addResponse.getResult().getString("transactionNo");
		
		InstorePrepayRequest payRequest = new InstorePrepayRequest();
		payRequest.setTransactionNo(transactionNo)
					.setPaymentBarcode("134668918993771844");
		
		InstorePrepayResponse payResponse = client.execute(payRequest);
		System.out.println("pay response:" + JSONObject.fromObject(payResponse));
		
	}
}
