package com.bl.common.cache.memcache;
//package com.bl.common.cache.memcache;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import net.spy.memcached.MemcachedClient;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cache.Cache;
//import org.springframework.cache.support.SimpleValueWrapper;
//
///**
// * Memcache Cache实现类
// * @author Jiarong
// * @since 2015.03.15
// */
//public class MemcachedCache implements Cache {
//	
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//	
//	private static final String PRESENT = new String();
//	
//	// 单个cache存储的key最大数量
//	private int maxElements = 10000;
//	
//	// 默认过期时间5分钟,单位秒
//	private int expire = 300;
//	
//	private String name;
//	
//	private MemcachedClient client;
//	
//	// 存储key的集合，使用LinkedHashMap实现
//	private KeySet keys;
//
//	public MemcachedCache() {}
//
//	public MemcachedCache(String name, int maxElements, int expire,  MemcachedClient client) {
//		this.name = name;
//		this.client = client;
//		this.maxElements = maxElements;
//		this.expire = expire;
//		this.keys = new KeySet(maxElements);
//		logger.info("init memcache, name: {}", name);
//	}
//	
//	@Override
//	public String getName() {
//		return this.name;
//	}
//
//	@Override
//	public Object getNativeCache() {
//		return this.client;
//	}
//
//	// ckey是key+cacheName作为前缀，也是最终存入缓存的key
//	@Override
//	public ValueWrapper get(Object key) {
//		if(logger.isDebugEnabled()){
//			logger.debug("get from memcache name: {}, key: {}", name, String.valueOf(key));
//		}
//		String ckey = toStringWithCacheName(key);
//		if (keys.containsKey(ckey)) {
//			Object value = client.get(ckey);
//			return value != null ? new SimpleValueWrapper(value) : null;
//		} else {
//			return null;
//		}
//	}
//
//	// 将ckey加入key集合并将ckey-value存入缓存
//	@Override
//	public void put(Object key, Object value) {
//		if(logger.isDebugEnabled()){
//			logger.debug("put into memcache name: {}, key: {}", name, String.valueOf(key));
//		}
//		if(value!=null){
//			String ckey = toStringWithCacheName(key);
//			keys.put(ckey, PRESENT);
//			client.set(ckey, expire, value);
//		}
//	}
//
//	// 从keys集合清除ckey，并从缓存清除
//	@Override
//	public void evict(Object key) {
//		if(logger.isDebugEnabled()){
//			logger.debug("evict memcache name: {}, key: {}", name, String.valueOf(key));
//		}
//		String ckey = toStringWithCacheName(key);
//		keys.remove(ckey);
//		client.delete(ckey);
//	}
//
//	private String toStringWithCacheName(Object obj) {
//		return name + "." + String.valueOf(obj);
//	}
//
//	// 遍历清除
//	@Override
//	public void clear() {
//		if(logger.isDebugEnabled()){
//			logger.debug("clear memcache name: {}", name);
//		}
//		for (String ckey : keys.keySet()) {
//			client.delete(ckey);
//		}
//		keys.clear();
//	}
//
//	public MemcachedClient getClient() {
//		return this.client;
//	}
//
//	public void setClient(MemcachedClient client) {
//		this.client = client;
//	}
//
//	public KeySet getKeys() {
//		return this.keys;
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public <T> T get(Object key, Class<T> type) {
//		if(logger.isDebugEnabled()){
//			logger.debug("get memcache name: {}, key: {}", name, String.valueOf(key));
//		}
//		String ckey = toStringWithCacheName(key);
//		if (keys.containsKey(ckey)) {
//			Object value = client.get(ckey);
//			return value != null ? (T)value : null;
//		} else {
//			return null;
//		}
//	}
//
//	@Override
//	public ValueWrapper putIfAbsent(Object key, Object value) {
//		if(logger.isDebugEnabled()){
//			logger.debug("putIfAbsent memcache name: {}, key: {}", name, String.valueOf(key));
//		}
//		String ckey = toStringWithCacheName(key);
//		if(keys.containsKey(ckey)){
//			Object existingValue = client.get(ckey);
//			if(existingValue==null){
//				client.set(ckey, expire, value);
//				return null;
//			}else{
//				return new SimpleValueWrapper(existingValue);
//			}
//		}else{
//			keys.put(ckey, PRESENT);
//			client.set(ckey, expire, value);
//			return null;
//		}
//	}
//
//	class KeySet extends LinkedHashMap<String, String> {
//		private static final long serialVersionUID = 1L;
//		private int maxSize;
//		
//		public KeySet(int initSize) {
//			super(initSize, 0.75F, true);
//			this.maxSize = initSize;
//		}
//		
//		public boolean removeEldestEntry(Map.Entry<String, String> eldest) {
//			boolean overflow = size() > this.maxSize;
//			if (overflow) {
//				MemcachedCache.this.client.delete(eldest.getKey());
//			}
//			return overflow;
//		}
//	}
//
//	public void setMaxElements(int maxElements) {
//		this.maxElements = maxElements;
//	}
//
//	public void setExpire(int expire) {
//		this.expire = expire;
//	}
//}
