package dataSearch;

import com.yuansfer.payment.client.YuanpayClient;
import com.yuansfer.payment.client.YuanpayV300Client;
import com.yuansfer.payment.request.dataSearch.RefundRequest;
import com.yuansfer.payment.request.dataSearch.TranQueryRequest;
import com.yuansfer.payment.response.dataSearch.RefundResponse;
import com.yuansfer.payment.response.dataSearch.TranQueryResponse;

import common.InitYuanpayConfig;
import net.sf.json.JSONObject;

public class DataSearchTest {

	public static void main(String[] args) {
		refund();
		
//		tranQuery();

	}
	
	
	public static void refund() {
		YuanpayClient client = new YuanpayV300Client(InitYuanpayConfig.initMerchantConfig());
		
		RefundRequest request = new RefundRequest();
		
		request.setRefundAmount("0.01")
				.setCurrency("USD")
				.setSettleCurrency("USD")
				.setRefundReference("refund" + System.nanoTime())
				.setTransactionNo("297553648150331212");
				
		RefundResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	
	public static void tranQuery() {
		YuanpayClient client = new YuanpayV300Client(InitYuanpayConfig.initMerchantConfig());
		
		TranQueryRequest request = new TranQueryRequest();
		request.setTransactionNo("297553648089730799");
		
		TranQueryResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	
}
