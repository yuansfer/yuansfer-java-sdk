package com.yuansfer.payment.enums;

public enum CurrencyEnums {
	USD("USD"),					//美金
	CNY("CNY"),					//人民币
	;
	private String value;
	
	private CurrencyEnums(String value) {
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
		for (CurrencyEnums en : CurrencyEnums.values()) {
			if (en.getValue().equals(value)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
