package com.yuansfer.payment.enums;

/**
 * vendor参数枚举
 * @author zhoukai
 *
 */
public enum VendorEnums {
	ALIPAY("alipay"),
	WECHATPAY("wechatpay"),
	UNIONPAY("unionpay"),
	CREDITCARD("creditcard"),
	;
	private String value;
	
	private VendorEnums(String value) {
        this.value = value;
    }
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public static boolean containValidate(String value) {
		boolean flag = false;
		for (VendorEnums en : VendorEnums.values()) {
			if (en.getValue().equals(value)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
