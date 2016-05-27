package com.bl.common.web.json;

/** 
 *
 * @author linjiarong
 * @date 2014年12月16日 下午12:23:35 
 * @version 0.0.1
 *  
 */
public class AnnotationSerializerConfig {
	
	private String annotation;
	
	private String serializer;
	
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public String getSerializer() {
		return serializer;
	}
	public void setSerializer(String serializer) {
		this.serializer = serializer;
	}

}
