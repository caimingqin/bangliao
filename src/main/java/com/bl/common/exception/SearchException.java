package com.bl.common.exception;

/**
 * 
 * 通用业务异常,应用业务逻辑出错应抛出此异常 
 *
 * @author Jiarong
 * @date 2014年11月2日 下午9:42:47 
 * @version 1.0.0
 *
 */
public class SearchException extends BaseException {
	
	/** 
	 * @Fields serialVersionUID 
	 */ 
	private static final long serialVersionUID = 8853729593799137275L;

	/**
     * 构造基础异常
     * @param code 异常代码
     * @param args 异常参数
     * @param defaultMessage 默认异常消息
     * @param cause 引起此异常的触发异常
     */
    public SearchException(String code, Object[] args, String defaultMessage, Throwable cause) {
    	super(code, args, defaultMessage,cause);
    }
    
    /**
     * 构造基础异常
     * @param code 异常代码
     * @param args 异常参数
     * @param defaultMessage 默认异常消息
     */
    public SearchException(String code, Object[] args, String defaultMessage) {
    	this(code,args,defaultMessage,null);
    }
    
    /**
     * 构造基础异常
     * @param defaultMessage 默认异常消息
     * @param cause 引起此异常的触发异常
     */
    public SearchException(String defaultMessage, Throwable cause) {
        this(null, null, defaultMessage, cause);
    }

    /**
     * 构造基础异常
     * @param defaultMessage 默认异常消息
     */
    public SearchException(String defaultMessage) {
        this(null, null, defaultMessage, null);
    }
    /**
     * 
     * 构造基础异常
     * @param code 异常代码
     * @param args 异常参数
     * @param cause 引起此异常的触发异常
     */
    public SearchException(String code, Object[] args, Throwable cause) {
        this(code, args, null, cause);
    }

    /**
     * 
     * 构造基础异常
     * @param code 异常代码
     * @param args 异常参数
     */
    public SearchException(String code, Object[] args) {
        this(code, args, null, null);
    }

}
