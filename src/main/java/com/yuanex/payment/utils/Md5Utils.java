package com.yuanex.payment.utils;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * @author zhoukai
 *
 */
public class Md5Utils implements Serializable {

	private static final long serialVersionUID = -7572925405603789118L;

	/**	
     * 用MD5算法进行加密
     *
     * @param hashStr
     *            Md5字符串
     * @return 加密后的字符串
     */
    public static String cryptHash(String hashStr) {
        String strCrypt = hashStr;
        if (strCrypt.length() > 0) {
            strCrypt = hash(strCrypt);
        }

        return strCrypt;
    }

    /**
     * MD5算法进行散列
     * 
     * @param str
     *            待散列的字符串
     * @return 散列后的字符串
     */
    public static  String hash(String str) {
    	try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(str.getBytes());  
            byte b[] = md.digest();  
  
            int i;  
  
            StringBuilder buf = new StringBuilder("");  
            for (int offset = 0; offset < b.length; offset++) {  
                i = b[offset];  
                if (i < 0) {
                	i += 256;  
                }
                if (i < 16) {
                	buf.append("0");  
                }
                buf.append(Integer.toHexString(i));  
            }  
            //32位加密  
            return buf.toString();  
            // 16位的加密  
            //return buf.toString().substring(8, 24);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            return null;  
        } 
    }
    
}
