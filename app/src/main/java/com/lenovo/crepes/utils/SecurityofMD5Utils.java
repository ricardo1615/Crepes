package com.lenovo.crepes.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityofMD5Utils {

	/**
	 * @param content  类型：String 需要加密的字符串
	 * @return  加密后的字符串
	 */
	public static String getMD5(String content) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(content.getBytes());
			return getHashString(digest);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getHashString(MessageDigest digest) {
		StringBuilder builder = new StringBuilder();
		for (byte b : digest.digest()) {
			builder.append(Integer.toHexString((b >> 4) & 0xf));
			builder.append(Integer.toHexString(b & 0xf));
		}
		return builder.toString();
	}
}
