/**   
 * TODO(用一句话描述该文件做什么) 
 * @author linjiarong 
 * @date 2014年12月16日 上午10:00:25 
 * @version 0.0.1
 */
package com.bl.common.web.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/** 
 * 普通String转化
 *
 * @author linjiarong
 * @date 2014年12月16日 上午10:00:25 
 * @version 0.0.1
 *  
 */
public class SimpleStringSerializer extends JsonSerializer<String>{

	/**
	 * @param value
	 * @param jgen
	 * @param provider
	 * @throws IOException
	 * @throws JsonProcessingException 
	 * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider) 
	 */
	@Override
	public void serialize(String value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeString(value);
	}
}
