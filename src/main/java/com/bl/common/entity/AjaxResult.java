package com.bl.common.entity;

public class AjaxResult {
	public static final String SUC_CODE = "1";
	public static final String DEF_FAIL_CODE = "0";
	public static String SUC_DEF_MSG = "成功！";
	public static String FAIL_DEF_MSG = "失败！";

	private String code;
	private String msg;
	private Object returnObj;

	private AjaxResult() {

	}

	private AjaxResult(String code, String msg, Object obj) {
		super();
		this.code = code;
		this.msg = msg;
		this.returnObj = obj;
	}

	public static AjaxResult fail() {
		return fail(FAIL_DEF_MSG);
	}

	public static AjaxResult fail(String msg) {
		return fail(DEF_FAIL_CODE, msg, null);
	}
	
	public static AjaxResult fail(String code,String msg) {
		return  fail(code, msg, null);
	}

	private static AjaxResult fail(String code,String msg, Object obj) {
		AjaxResult result = new AjaxResult(code, msg, obj);
		return result;
	}
	

	public static AjaxResult suc() {
		return suc(SUC_CODE,SUC_DEF_MSG, null);
	}

	public static AjaxResult suc(Object returnObj) {
		return suc(SUC_CODE,SUC_DEF_MSG, returnObj);
	}
	
	public static AjaxResult suc(String msg,Object returnObj) {
		return suc(SUC_CODE,msg, returnObj);
	}

	private static AjaxResult suc(String code,String msg, Object obj) {
		AjaxResult executeResult = new AjaxResult(code, msg, obj);
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
		return returnObj;
	}

}
