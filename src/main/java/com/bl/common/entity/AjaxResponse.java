package com.bl.common.entity;

/**
 * 
 * Ajax返回信息，由前端ExceptionHandler处理，配置前端Ajax请求使用。 
 * 用于自动向用户展示Ajax请求异常信息。
 * 
 * @author Jiarong
 * @date 2014年12月10日 下午4:40:24 
 * @version 0.0.1
 *
 */
public class AjaxResponse {
	
	/** 请求状态栏 */
    private Boolean success;
    
    /** 请求状态描述 */
    private String message;
    
    /** 请求数据内容 */
    private Object content;
    
    /** 请求异常信息 */
    private Object exception;

    public AjaxResponse() {
        this(Boolean.TRUE, "操作成功");
    }

    public AjaxResponse(Boolean success) {
        this(success, null);
    }

    public AjaxResponse(String message) {
        this(Boolean.TRUE, "操作成功");
    }
    
	public AjaxResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
        if (this.message == null) {
            if (Boolean.FALSE.equals(success)) {
                this.message = "操作失败";
            }
            if (Boolean.TRUE.equals(success)) {
                this.message = "操作成功";
            }

        }
    }


    public AjaxResponse(Boolean success, String message, Object content) {
		super();
		this.success = success;
		this.message = message;
		this.content = content;
	}

	public static AjaxResponse fail() {
        return fail(null);
    }

    public static AjaxResponse fail(String message) {
        return new AjaxResponse(Boolean.FALSE, message);
    }

    public static AjaxResponse success() {
        return success(null);
    }

    public static AjaxResponse success(String message) {
        return new AjaxResponse(Boolean.TRUE, message);
    }
    
    public static AjaxResponse successContent(Object content){
    	AjaxResponse res = AjaxResponse.success();
    	res.setContent(content);
    	return res;
    }
    public static AjaxResponse successContent(String message,Object content){
    	 return new AjaxResponse(Boolean.TRUE,message,content);
    }
    
    public static AjaxResponse failException(Object exception){
    	AjaxResponse res = AjaxResponse.fail();
    	res.setException(exception);
    	return res;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public Object getException() {
		return exception;
	}

	public void setException(Object exception) {
		this.exception = exception;
	}
}
