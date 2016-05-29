package com.bl.common.entity;

import java.io.Serializable;

import com.bl.common.exception.BusinessException;

/**
 * 
 * api请求参数
 * 
 * @Copyright Copyright © 2016 http://www.ibangliao.com/ Inc. All rights
 *            reserved.
 * @Company 帮聊科技
 * @Author <a href="mailto:email">moonkingcai@163.com</a>
 * @CreateDate 2016年5月26日 下午8:37:59
 * @Since V1.0
 * @Version V1.0
 */
public class AppRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 应用KEY
	 */
	private String appKey;
	/**
	 * 秘钥
	 */
	private String sign;
	/**
	 * 接口版本号
	 */
	private int version;
	/**
	 * 设备ID
	 */
	private String devId;
	/**
	 * 手机系统（ios, android）
	 */
	private String osType;
	/**
	 * 网络环境（2G、3G、4G还是WIFI）
	 */
	private String netType;
	/**
	 * 发送请求的时间，格式"yyyy-MM-dd HH:mm:ss"
	 */
	private String timestamp;
	/**
	 * 用户令牌（秘钥加密所以可以不需要传）
	 */
	private String token;
	/**
	 * "请求参数对象或者集合"
	 */
	private Object body;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public String bodyToString() {
		if (body instanceof String) {
			return (String) body;
		} else {
			throw new BusinessException("body 非字符串类型");
		}

	}

	@Override
	public String toString() {
		return "AppRequest [appKey=" + appKey + ", sign=" + sign + ", version=" + version + ", devId=" + devId
				+ ", osType=" + osType + ", netType=" + netType + ", timestamp=" + timestamp + ", token=" + token
				+ ", body=" + body + "]";
	}

}
