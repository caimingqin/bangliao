package com.bl.common.web.json;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

/** 
 *
 * @author linjiarong
 * @date 2014年12月12日 下午1:56:15 
 * @version 0.0.1
 *  
 */
public class ContextualStringSerializer extends JsonSerializer<String>
	implements ContextualSerializer{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private List<AnnotationSerializerConfig> config;

	/**
	 * @param prov
	 * @param property
	 * @return
	 * @throws JsonMappingException 
	 * @see com.fasterxml.jackson.databind.ser.ContextualSerializer#createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty) 
	 */
	@Override
	public JsonSerializer<String> createContextual(SerializerProvider prov,
			BeanProperty property) throws JsonMappingException {
		// First find annotation used for getter or field:
		//JsonDict ann = getAnnotation(property);
		try {
			for(AnnotationSerializerConfig conf : config){
				Class annoClass;
				annoClass = Class.forName(conf.getAnnotation());
				if(property!=null){
					Object anno = property.getAnnotation(annoClass);
					if(anno!=null){
						Class serializerClass = Class.forName(conf.getSerializer());
						Object serInst = serializerClass.newInstance();
						PropertyUtils.setProperty(serInst, "anno", anno);
						return (JsonSerializer<String>) serInst;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new SimpleStringSerializer();
		
		
	}

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
	public void setConfig(List<AnnotationSerializerConfig> config) {
		this.config = config;
	}

}
