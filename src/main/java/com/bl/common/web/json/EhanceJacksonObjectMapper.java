/**   
 * TODO(用一句话描述该文件做什么) 
 * @author linjiarong 
 * @date 2014年12月12日 下午3:24:40 
 * @version 0.0.1
 */
package com.bl.common.web.json;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/** 
 * TODO(这里用一句话描述作用) 
 *
 * @author linjiarong
 * @date 2014年12月12日 下午3:24:40 
 * @version 0.0.1
 *  
 */
public class EhanceJacksonObjectMapper extends ObjectMapper implements InitializingBean{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4345520483781905421L;

	private String modelName;
	
	private String version;
	
	private List<EhanceJacksonSerializer> serializers;	
	
	public EhanceJacksonObjectMapper() {
		
	}


	public String getModelName() {
		return modelName;
	}


	public void setModelName(String modelName) {
		this.modelName = modelName;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public void setSerializers(List<EhanceJacksonSerializer> serializers) {
		this.serializers = serializers;
	}


	/**
	 * TODO 
	 * @throws Exception 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet() 
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		String[] vers = version.split("\\.");
		SimpleModule module = new SimpleModule(modelName, new Version(Integer.parseInt(vers[0]), 
																	  Integer.parseInt(vers[1]), 
																	  Integer.parseInt(vers[2]), 
																	  "", null, null));
		if(serializers!=null){
			for(EhanceJacksonSerializer serializer : serializers){
				try {
					module.addSerializer(Class.forName(serializer.getType()), 
										 serializer.getSerializer());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		super.registerModule(module);
	}
}
