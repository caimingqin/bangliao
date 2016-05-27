package com.bl.common.entity;

import java.lang.reflect.Method;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import com.bl.common.utils.IdUtil;

public class BaseEntityAdvice implements MethodBeforeAdvice{
    private Logger logger=LoggerFactory.getLogger(BaseEntityAdvice.class.getName());
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String methodName = method.getName();
		BaseEntity bs=(BaseEntity) args[0];
		if(methodName.equalsIgnoreCase("save")){
			bs.setCreateDate(new Date());
			bs.setId(IdUtil.getUUId());
			bs.setCreateBy("caimingqin");
		}else if(methodName.equalsIgnoreCase("update")
				||methodName.equalsIgnoreCase("deleteByEntity")
				){
			bs.setUpdateDate(new Date());
			bs.setUpdateBy("updateByCcccc");
		}
		logger.debug("methodName:"+method.getName());
	}
	

}
