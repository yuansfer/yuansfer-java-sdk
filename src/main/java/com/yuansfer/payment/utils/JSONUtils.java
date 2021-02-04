package com.yuansfer.payment.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JSONUtils.class);
	
	public static boolean isStringJsonFormat(String str) {
		try {
			JSONObject json = JSONObject.fromObject(str);
			logger.info(json.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean isStringJsonArrFormat(String str) {
		try {
			JSONArray jsonArr = JSONArray.fromObject(str);
			logger.info(jsonArr.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
