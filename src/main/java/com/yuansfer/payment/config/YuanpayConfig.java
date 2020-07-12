package com.yuansfer.payment.config;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yuansfer.payment.enums.EnviromentEnums;
import com.yuansfer.payment.exception.YuanpayException;

/**
 * 圆支付配置信息
 * @author zhoukai
 *
 */
public class YuanpayConfig implements Serializable {
	private static final long serialVersionUID = -4008739773524288292L;
	private String env;						//环境类型
	private Integer merGroupSwitch = 0;		//是否开启服务商功能
	private String merGroupNo;				//服务商号
	private String merchantNo;				//商户号
	private String storeNo;					//店铺号
	private String storeAdminNo;			//店员号
	private String token;					//token
	
	//给param中增加账户相关信息
	public Map<String, String> basicParamSetting(Map<String, String> param) {
		if (1 == this.merGroupSwitch) {
			param.put("merGroupNo", this.merGroupNo);
		}
		param.put("merchantNo", this.merchantNo);
		param.put("storeNo", this.storeNo);
		
		if (StringUtils.isNotEmpty(this.storeAdminNo)) {
			param.put("storeAdminNo", this.storeAdminNo);
		}
		return param;
	}
	
	public void basicValidate() {
		if (StringUtils.isEmpty(this.env)) {
			throw new YuanpayException("env missing");
		} else {
			boolean flag = false;
			for (EnviromentEnums en : EnviromentEnums.values()) {
				if (en.getValue().equals(this.env)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				throw new YuanpayException("data error: env");
			}
		}
		if (null == this.merGroupSwitch) {
			throw new YuanpayException("merGroupSwitch missing");
		}
		if (0 != this.merGroupSwitch && 1 != this.merGroupSwitch) {
			throw new YuanpayException("data error: merGroupSwitch");
		}
		if (1 ==  this.merGroupSwitch) {
			if (StringUtils.isEmpty(this.merGroupNo)) {
				throw new YuanpayException("merGroupNo missing");
			}
		}
		if (StringUtils.isEmpty(this.merchantNo)) {
			throw new YuanpayException("merchantNo missing");
		}
		if (StringUtils.isEmpty(this.storeNo)) {
			throw new YuanpayException("storeNo missing");
		}
	}
	

	public String getMerGroupNo() {
		return merGroupNo;
	}

	public YuanpayConfig setMerGroupNo(String merGroupNo) {
		this.merGroupNo = merGroupNo;
		return this;
	}

	public Integer getMerGroupSwitch() {
		return merGroupSwitch;
	}

	public YuanpayConfig setMerGroupSwitch(Integer merGroupSwitch) {
		this.merGroupSwitch = merGroupSwitch;
		return this;
	}
	
	public String getMerchantNo() {
		return merchantNo;
	}
	public YuanpayConfig setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
		return this;
	}
	public String getStoreNo() {
		return storeNo;
	}
	public YuanpayConfig setStoreNo(String storeNo) {
		this.storeNo = storeNo;
		return this;
	}
	public String getEnv() {
		return env;
	}
	public YuanpayConfig setEnv(String env) {
		this.env = env;
		return this;
	}
	public String getStoreAdminNo() {
		return storeAdminNo;
	}
	public YuanpayConfig setStoreAdminNo(String storeAdminNo) {
		this.storeAdminNo = storeAdminNo;
		return this;
	}

	public String getToken() {
		return token;
	}

	public YuanpayConfig setToken(String token) {
		this.token = token;
		return this;
	}
}
