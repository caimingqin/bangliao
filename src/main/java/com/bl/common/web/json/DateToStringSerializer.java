
package com.bl.common.web.json;

import java.io.IOException;
import java.util.Date;

import com.bl.common.utils.date.DateUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 
 * 
 * @Copyright Copyright © 2016 http://www.wanzhongonline.com/ Inc. All rights reserved.
 * @Company 万众科技
 * @Author <a href="mailto:email">caimingqin@revenco.com</a>
 * @CreateDate 2016年5月23日 下午8:48:02
 * @Since V1.0
 * @Version V1.0
 */
public class DateToStringSerializer extends JsonSerializer<Date>{

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		//TODO try convert
	     String dateStr = DateUtil.convertDateToStr(value, DateUtil.DEFAULT_DATE_TIME_FORMAT);
		jgen.writeString(dateStr);
		
	}

	
}
