package dataSearch;

import com.yuansfer.payment.client.YuanpayClient;
import com.yuansfer.payment.client.YuanpayV200Client;
import com.yuansfer.payment.request.dataSearch.RefundRequest;
import com.yuansfer.payment.request.dataSearch.TranQueryRequest;
import com.yuansfer.payment.request.dataSearch.TransListRequest;
import com.yuansfer.payment.response.dataSearch.RefundResponse;
import com.yuansfer.payment.response.dataSearch.TranQueryResponse;
import com.yuansfer.payment.response.dataSearch.TransListResponse;

import common.InitYuanpayConfig;
import net.sf.json.JSONObject;

public class DataSearchTest {

	public static void main(String[] args) {
//		refund();
		
//		tranQuery();

//		transList();
	}
	
	
	public static void refund() {
		YuanpayClient client = new YuanpayV200Client(InitYuanpayConfig.initMerchantConfig());
		
		RefundRequest request = new RefundRequest();
		
		request.setAmount("0.01")
				.setRefundReference("refund" + System.nanoTime())
				.setTransactionNo("297553630266977466");
				
		RefundResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	
	public static void tranQuery() {
		YuanpayClient client = new YuanpayV200Client(InitYuanpayConfig.initMerchantConfig());
		
		TranQueryRequest request = new TranQueryRequest();
		request.setTransactionNo("297553630266977466");
		
		TranQueryResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
	
	
	public static void transList() {
		YuanpayClient client = new YuanpayV200Client(InitYuanpayConfig.initMerchantConfig());
		
		TransListRequest request = new TransListRequest();
		request.setStartDate("20200712")
				.setEndDate("20200712");
		
		TransListResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
}
