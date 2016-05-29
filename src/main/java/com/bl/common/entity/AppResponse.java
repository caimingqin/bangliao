package com.bl.common.entity;

import java.io.Serializable;
/**
 * 
 *api执行返回结果 
 * @Copyright Copyright © 2016 http://www.ibangliao.com/ Inc. All rights reserved.
 * @Company 帮聊科技
 * @Author <a href="mailto:email">moonkingcai@163.com</a>
 * @CreateDate 2016年5月29日 上午11:47:20
 * @Since V1.0
 * @Version V1.0
 */
public class AppResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6124908885074521513L;
	public static final String SUC_CODE = "1";
	public static final String DEF_FAIL_CODE = "0";
	public static String SUC_DEF_MSG = "成功！";
	public static String FAIL_DEF_MSG = "失败！";

	private String code;
	private String msg;
	private Object data;

	private AppResponse() {

	}

	private AppResponse(String code, String msg, Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = obj;
	}

	public static AppResponse fail() {
		return fail(FAIL_DEF_MSG);
	}

	public static AppResponse fail(String msg) {
		return fail(DEF_FAIL_CODE, msg, null);
	}
	
	public static AppResponse fail(String code,String msg) {
		return  fail(code, msg, null);
	}

	private static AppResponse fail(String code,String msg, Object obj) {
		AppResponse result = new AppResponse(code, msg, obj);
		return result;
	}
	

	public static AppResponse suc() {
		return suc(SUC_CODE,SUC_DEF_MSG, null);
	}

	public static AppResponse suc(Object returnObj) {
		return suc(SUC_CODE,SUC_DEF_MSG, returnObj);
	}
	
	public static AppResponse suc(String msg,Object returnObj) {
		return suc(SUC_CODE,msg, returnObj);
	}

	private static AppResponse suc(String code,String msg, Object obj) {
		AppResponse executeResult = new AppResponse(code, msg, obj);
		return executeResult;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getReturnObj() {
		return data;
	}


}
