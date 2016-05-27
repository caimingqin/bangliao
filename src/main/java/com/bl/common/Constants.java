/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.bl.common;

/**
 * <p>User: Kaitao
 * <p>Date: 13-2-7 下午5:14
 * <p>Version: 1.0
 */
public interface Constants {
    /**
     * 操作名称
     */
    String OP_NAME = "op";


    /**
     * 消息key
     */
    String MESSAGE = "message";

    /**
     * 错误key
     */
    String ERROR = "error";

    /**
     * 上个页面地址
     */
    String BACK_URL = "BackURL";

    String IGNORE_BACK_URL = "ignoreBackURL";

    /**
     * 当前请求的地址 带参数
     */
    String CURRENT_URL = "currentURL";

    /**
     * 当前请求的地址 不带参数
     */
    String NO_QUERYSTRING_CURRENT_URL = "noQueryStringCurrentURL";

    String CONTEXT_PATH = "ctx";

    /**
     * 当前登录的用户
     */
    String CURRENT_USER = "user";
    String CURRENT_USERNAME = "username";

    String ENCODING = "UTF-8";
    
    //未删除
    public static final String UN_DELETED = "0";
    //已删除
    public static final String DELETED = "1";
    
    public static final String AVAILABLE_YES ="1" ;
    
    public static final String AVAILABLE_NO ="0" ; 

    public static final String AVAILABLE_CNYES ="是" ;
    
    public static final String AVAILABLE_CNNO ="否" ; 
    public static final String AUTH_JOB ="0" ; 
    
    public static final String COMMA=",";

}
