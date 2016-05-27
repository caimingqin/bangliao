package com.bl.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5工具类
 * 
 * @Copyright Copyright © 2016 http://www.wanzhongonline.com/ Inc. All rights reserved.
 * @Company 万众科技
 * @Author <a href="mailto:lixiaolong@revenco.com">lixiaolong</a>
 * @CreateDate 2016-1-11 下午2:24:09
 * @Since V1.0
 * @Version V1.0
 */
public class MD5 {

	/**
	 * 签名字符串
	 * @param text 需要签名的字符串
	 * @param key 密钥
	 * @param input_charset 编码格式
	 * @return 签名结果
	 */
	public static String sign(String text, String key, String input_charset) {
		text = text + key;
		return DigestUtils.md5Hex(getContentBytes(text, input_charset));
	}

	/**
	 * 签名字符串
	 * @param text 需要签名的字符串
	 * @param sign 签名结果
	 * @param key 密钥
	 * @param input_charset 编码格式
	 * @return 签名结果
	 */
	public static boolean verify(String text, String sign, String key, String input_charset) {
		text = text + key;
		String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
		if (mysign.equals(sign)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param content
	 * @param charset
	 * @return
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException 
	 */
	private static byte[] getContentBytes(String content, String charset) {
		if (charset == null || "".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
	}
	
	public static void main(String[] args) {
		String sign = sign("2c94b422fa4015219f608540001", "300099987771223", "utf-8");
		System.out.println(sign);
		boolean verify = verify("2c94b422fa4015219f608540001", sign, "300099987771223", "utf-8");
		System.out.println(verify);
	}

}