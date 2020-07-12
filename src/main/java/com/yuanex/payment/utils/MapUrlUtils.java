package com.yuanex.payment.utils;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * map和url参数形式互转工具类
 * 
 * @author zhoukai
 *
 */
public class MapUrlUtils implements Serializable{
	private static final long serialVersionUID = -6841972948480106892L;


	/**
	 * 将map转换成url
	 * 
	 * @param map
	 * @return
	 */
	public static String getUrlParamsByMap(Map<String, String> map) {
		if (map == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}
	
}
