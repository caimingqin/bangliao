package com.bl.common.utils;

import java.util.UUID;

public class IdUtil {

	public static String getUUId(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
