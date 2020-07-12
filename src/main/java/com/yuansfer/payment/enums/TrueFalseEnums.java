package com.yuanex.payment.enums;

public enum TrueFalseEnums {
	TRUE("true"),
	FALSE("false"),
	;
	private String value;
	
	private TrueFalseEnums(String value) {
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
		for (TrueFalseEnums en : TrueFalseEnums.values()) {
			if (en.getValue().equals(value)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
