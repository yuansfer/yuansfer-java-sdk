package com.yuansfer.payment.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReflectUtils implements Serializable {

	private static final long serialVersionUID = 2857559294137185174L;

	/*
	 * 将对象类型转化为map类型
	 */
	public static <T> Map<String, String> obj2map(T t, List<String> ignores) {
		try {
			Map<String, String> map = new TreeMap<String, String>();
			
			Field[] fields = t.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object obj = field.get(t);
				if (null != obj) {
					if (null != ignores && !ignores.contains(field.getName())) {
						map.put(field.getName(), obj.toString());
					}
				}
			}
			
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
