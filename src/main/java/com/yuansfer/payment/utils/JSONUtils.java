package com.yuansfer.payment.utils;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtils {
	
	private static final Logger logger = Logger.getLogger(JSONUtils.class);
	
	public static boolean isStringJsonFormat(String str) {
		try {
			JSONObject json = JSONObject.fromObject(str);
			logger.info(json);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public static boolean isStringJsonArrFormat(String str) {
		try {
			JSONArray jsonArr = JSONArray.fromObject(str);
			logger.info(jsonArr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
