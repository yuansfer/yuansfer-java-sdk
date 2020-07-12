package com.yuanex.payment.enums;

/**
 * terminal参数枚举
 * @author zhoukai
 *
 */
public enum TerminalEnums {
	ONLINE("ONLINE"),
	WAP("WAP"),
	;
	private String value;
	
	private TerminalEnums(String value) {
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
		for (TerminalEnums en : TerminalEnums.values()) {
			if (en.getValue().equals(value)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
