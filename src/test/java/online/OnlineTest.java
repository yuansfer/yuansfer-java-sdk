package online;

import com.yuansfer.payment.client.YuanpayClient;
import com.yuansfer.payment.client.YuanpayV300Client;
import com.yuansfer.payment.request.online.OnlineSecurepayRequest;
import com.yuansfer.payment.response.online.OnlineSecurepayResponse;

import common.InitYuanpayConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OnlineTest {

	public static void main(String[] args) {
		YuanpayClient client = new YuanpayV300Client(InitYuanpayConfig.initMerchantConfig());
		
		JSONArray goods = new JSONArray();
		JSONObject item = new JSONObject();
		item.put("goods_name","name1");
		item.put("quantity", "1");
		goods.add(item);
		
		
		OnlineSecurepayRequest request = new OnlineSecurepayRequest();
		request.setAmount("0.01")
				.setCurrency("USD")
				.setSettleCurrency("USD")
				.setVendor("alipay")
				.setTerminal("ONLINE")
				.setReference(System.nanoTime()+"")
				.setIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
				.setCallbackUrl("http://zk-tys.yunkeguan.com/ttest/test2?status={status}&amount={amount}&transactionNo={transactionNo}")
				.setDescription("testDescription")
				.setNote("testNote")
				.setGoodsInfo(goods.toString());
		
		OnlineSecurepayResponse response = client.execute(request);
		System.out.println(JSONObject.fromObject(response));
	}
}
