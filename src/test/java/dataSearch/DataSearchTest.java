package dataSearch;

import com.yuanex.payment.client.YuanpayClient;
import com.yuanex.payment.client.YuanpayV200Client;
import com.yuanex.payment.request.dataSearch.RefundRequest;
import com.yuanex.payment.request.dataSearch.TranQueryRequest;
import com.yuanex.payment.request.dataSearch.TransListRequest;
import com.yuanex.payment.response.dataSearch.RefundResponse;
import com.yuanex.payment.response.dataSearch.TranQueryResponse;
import com.yuanex.payment.response.dataSearch.TransListResponse;

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
