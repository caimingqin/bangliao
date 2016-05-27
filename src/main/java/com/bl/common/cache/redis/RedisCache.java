package com.bl.common.cache.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import redis.clients.jedis.JedisCluster;

public class RedisCache implements Cache {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 默认过期时间5分钟,单位秒
	private int expire = 300;

	private String name;

	private JedisCluster client;

	public RedisCache() {
	}

	public RedisCache(String name,Integer expire, JedisCluster client) {
		this.name = name;
		this.client = client;
		if (expire != null) {
			this.expire = expire;
		}
		logger.info("init redis, name: {}", name);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Object getNativeCache() {
		return this.client;
	}

	// ckey是key+cacheName作为前缀，也是最终存入缓存的key
	@Override
	public ValueWrapper get(Object key) {
		if (logger.isDebugEnabled()) {
			logger.debug("ValueWrapper get from redis name: {}, key: {}", name, String.valueOf(key));
		}
		byte[] ckey = toStringWithCacheName(key);
			byte[] bytes = client.get(ckey);
			Object object = ObjectSerializeUtil.toObject(bytes);
			return object != null ? new SimpleValueWrapper(object) : null;
	}

	// 将ckey加入key集合并将ckey-value存入缓存
	@Override
	public void put(Object key, Object value) {
		if (logger.isDebugEnabled()) {
			logger.debug("put into redis name: {}, key: {}", name, String.valueOf(key));
		}
		if (value != null) {
			byte[] ckey = toStringWithCacheName(key);
			client.set(ckey, ObjectSerializeUtil.toBytes(value));
			client.expire(ckey, this.expire);
		}
	}

	// 从keys集合清除ckey，并从缓存清除
	@Override
	public void evict(Object key) {
		if (logger.isDebugEnabled()) {
			logger.debug("evict redis name: {}, key: {}", name, String.valueOf(key));
		}
		byte[] ckey = toStringWithCacheName(key);
		client.del(ckey);
	}

	private byte[] toStringWithCacheName(Object obj) {
		String key = name + "." + String.valueOf(obj);
		return key.getBytes();
	}

	// 遍历清除
	@Override
	public void clear() {
		
	}

	public JedisCluster getClient() {
		return this.client;
	}

	public void setClient(JedisCluster client) {
		this.client = client;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object key, Class<T> type) {
		if (logger.isDebugEnabled()) {
			logger.debug("T get redis name: {}, key: {}", name, String.valueOf(key));
		}
		byte[] ckey = toStringWithCacheName(key);
			byte[] bytes = client.get(ckey);
			Object object = ObjectSerializeUtil.toObject(bytes);
			if(object!=null){
				return (T)object;
			}
			return null;
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		if (logger.isDebugEnabled()) {
			logger.debug("putIfAbsent redis name: {}, key: {}", name, String.valueOf(key));
		}
		byte[] ckey = toStringWithCacheName(key);
		byte[] bytes = client.get(ckey);
		if (bytes == null) {
			client.set(ckey, bytes);
			client.expire(ckey, this.expire);
			return null;
		} else {
			return new SimpleValueWrapper(value);
		}

	}

	public void setExpire(int expire) {
		this.expire = expire;
	}
}
