/**   
 * TODO(用一句话描述该文件做什么) 
 * @author linjiarong 
 * @date 2014年12月12日 下午3:34:51 
 * @version 0.0.1
 */
package com.bl.common.web.json;

import com.fasterxml.jackson.databind.JsonSerializer;

/** 
 * TODO(这里用一句话描述作用) 
 *
 * @author linjiarong
 * @date 2014年12月12日 下午3:34:51 
 * @version 0.0.1
 *  
 */
public class EhanceJacksonSerializer {
	
	private String type;
	private JsonSerializer serializer;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;	
	}
	public JsonSerializer getSerializer() {
		return serializer;
	}
	public void setSerializer(JsonSerializer serializer) {
		this.serializer = serializer;
	}

}
