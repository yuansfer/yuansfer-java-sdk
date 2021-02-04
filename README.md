# Yuansfer JAVA SDK

[Yuansfer API](https://docs.yuansfer.com/)


## Requirements

- JAVA >= 1.6


## Installation
```maven
<dependency>
    <groupId>com.yuansfer</groupId>
    <artifactId>yuansfer-payment</artifactId>
    <version>3.0.0</version>
</dependency>
```

## Usage

Please see [examples](https://github.com/yuansfer/yuansfer-java-sdk/tree/master/src/test/java)

### 1. Init
```java
YuanpayConfig config = new YuanpayConfig();
config.setEnv(EnviromentEnums.SANDBOX.getValue())
      .setMerchantNo("200043")
      .setStoreNo("300014")
      .setToken("5cbfb079f15b150122261c8537086d77a");

YuanpayClient client = new YuanpayV200Client(config);                
```



### 2. Online API
```java
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
		.setTerminal("WAP")
		.setReference(System.nanoTime()+"")
		.setIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
		.setCallbackUrl("http://zk-tys.yunkeguan.com/ttest/test2?status={status}&amount={amount}&transactionNo={transactionNo}")
		.setDescription("testDescription")
		.setNote("testNote")
		.setGoodsInfo(goods.toString());

OnlineSecurepayResponse response = client.execute(request);
System.out.println(JSONObject.fromObject(response));        
```

### 2. Offline API
```java
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
```

### 3. Mobile API
```java
YuanpayClient client = new YuanpayV300Client(InitYuanpayConfig.initMerchantConfig());
MobilePrepayRequest request = new MobilePrepayRequest();
request.setAmount("0.01")
		.setCurrency("USD")
		.setSettleCurrency("USD")
		.setDescription("testDescription")
		.setIpnUrl("http://zk-tys.yunkeguan.com/ttest/test")
		.setNote("testNote")
		.setReference(System.nanoTime()+"")
		.setTerminal("APP")
		.setVendor("alipay");

MobilePrepayResponse response = client.execute(request);
System.out.println(JSONObject.fromObject(response));
```

### 3. Data API
```java
YuanpayClient client = new YuanpayV300Client(InitYuanpayConfig.initMerchantConfig());
		
RefundRequest request = new RefundRequest();

request.setRefundAmount("0.01")
		.setCurrency("USD")
		.setSettleCurrency("USD")
		.setRefundReference("refund" + System.nanoTime())
		.setTransactionNo("297553648150331212");
		
RefundResponse response = client.execute(request);
System.out.println(JSONObject.fromObject(response));
```


