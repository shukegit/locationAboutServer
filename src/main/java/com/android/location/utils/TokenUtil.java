package com.android.location.utils;

import java.util.UUID;

public class TokenUtil {
	
	public static String createToken() {
		String token = UUID.randomUUID().toString().replace("-", "");
		return token;
 	}
}
