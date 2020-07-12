package common;
import com.yuanex.payment.config.YuanpayConfig;
import com.yuanex.payment.enums.EnviromentEnums;

public class InitYuanpayConfig {

	public static YuanpayConfig initMerchantConfig() {
		YuanpayConfig config = new YuanpayConfig();
		config.setEnv(EnviromentEnums.SANDBOX.getValue())
				.setMerchantNo("200043")
				.setStoreNo("300014")
				.setToken("5cbfb079f15b150122261c8537086d77a");
		return config;
	}
	
	
	public static YuanpayConfig initIsvConfig() {
		YuanpayConfig config = new YuanpayConfig();
		config.setEnv(EnviromentEnums.SANDBOX.getValue())
				.setMerGroupNo("900001")
				.setMerchantNo("200043")
				.setStoreNo("300014")
				.setToken("e9ae88025307fa98971821b22b55fa6b");
		return config;
	}
}
