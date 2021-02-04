package com.yuansfer.payment.enums;

/**
 * 
 * @author yuansfer
 *
 */
public enum EnviromentEnums {
	SANDBOX("sandbox"),					//沙箱
	PRODUCT("product"),					//生产
	;
	private String value;
	
	private EnviromentEnums(String value) {
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
		for (EnviromentEnums en : EnviromentEnums.values()) {
			if (en.getValue().equals(value)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
