package com.bl.common.database;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

public class DataSourceAdvice implements MethodBeforeAdvice{
    private Logger logger=LoggerFactory.getLogger(DataSourceAdvice.class.getName());
	private String queryMethodNames;
	private String readDataSourseBeanId;
	private String writeDataSourceBeanId;
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		logger.debug("methodName:"+method.getName());
		if(this.queryMethodNames!=null && this.queryMethodNames.contains(method.getName())){
			DataSourceExchange.setDataSource(readDataSourseBeanId);
		}else{
			DataSourceExchange.setDataSource(writeDataSourceBeanId);
		}
	}
	public void setQueryMethodNames(String queryMethodNames) {
		this.queryMethodNames = queryMethodNames;
	}
	public void setReadDataSourseBeanId(String readDataSourseBeanId) {
		this.readDataSourseBeanId = readDataSourseBeanId;
	}
	public void setWriteDataSourceBeanId(String writeDataSourceBeanId) {
		this.writeDataSourceBeanId = writeDataSourceBeanId;
	}
	

}
