package com.android.location.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	public static String getMD5Code(String code) {
		
		System.out.println("加密前的密码是：" + code);
		try {
			//指定加密方式
			MessageDigest md5 = MessageDigest.getInstance("md5");
			//要加密的数据
			byte[] b = code.getBytes();
			//加密
			byte[] digest = md5.digest(b);
			//十六进制的字符
			char[] chars = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8','9','A','B','C','D','E','F'};
			StringBuffer buffer = new StringBuffer();
			
			//处理成十六进制的字符串
			for(byte bb : digest) {
				buffer.append(chars[(bb >> 4) & 15]);
				buffer.append(chars[bb & 15]);
			}
			System.out.println("加密后的密码是：" + buffer.toString());
			
			//返回加密后的字符串
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

}
