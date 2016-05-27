package com.common.utils;

import java.util.Date;

import org.junit.Test;

import com.bl.common.utils.IdUtil;
import com.bl.common.web.json.DateToStringSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class ObjectMapperTest {

	@Test
	public void test() throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();  
		module.addSerializer(Date.class, new DateToStringSerializer());  
		mapper.registerModule(module);  
		 User user = new User();
		 user.setCreate(new Date());
		 user.setId(IdUtil.getUUId());
		String serialized = mapper.writeValueAsString(user); 
		
		System.out.println(serialized);
	}
}
