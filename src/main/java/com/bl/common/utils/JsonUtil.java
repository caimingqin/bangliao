package com.bl.common.utils;

import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	public static ObjectMapper readMapper = new ObjectMapper();

	static {
		readMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		readMapper.configure(Feature.ALLOW_COMMENTS, true);
		readMapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		readMapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}

	/**
	 * (json转为Object)
	 * 
	 * @param json
	 * @param toClass
	 * @return
	 */
	public static <T> T parseToObject(String json, Class<T> toClass) {
		try {
			return (T) readMapper.readValue(json, toClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * (json转为Object)
	 * 
	 * @param json
	 * @param toClass
	 * @return
	 */
	public static <T> T parseToObject(InputStream inputJson, Class<T> toClass) {
		try {
			return (T) readMapper.readValue(inputJson, toClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static String toJson(Object value) {
		try {
			return readMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return null;
	}
}