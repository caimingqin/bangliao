package com.bl.common.cache.memcache;
//package com.bl.common.cache.memcache;
//
//import java.util.Collection;
//
//import net.spy.memcached.MemcachedClient;
//
//import org.springframework.cache.Cache;
//import org.springframework.cache.support.AbstractCacheManager;
//
///**
// * Memcached cache manager
// * 
// * @author Jiarong
// *
// */
//
//public class MemcachedCacheManager extends AbstractCacheManager {
//	
//	// 单个cache存储的key最大数量
//	private int maxElements = 10000;
//	// 默认过期时间10天,单位s
//	private int expire = 300;
//
//	private MemcachedClient client;
//
//	public MemcachedCacheManager() {
//	}
//
//	public MemcachedCacheManager(MemcachedClient client) {
//		this.client = client;
//	}
//
//	public void setClient(MemcachedClient client) {
//		this.client = client;
//	}
//
//	// AbstractCacheManager.afterPropertiesSet不允许loadCaches返回空，所以覆盖掉
//	public void afterPropertiesSet() {
//	}
//
//	protected Collection<? extends Cache> loadCaches() {
//		return null;
//	}
//
//	// 根据名称获取cache，对应注解里的value如notice_cache，没有就创建并加入cache管理
//	public Cache getCache(String name) {
//		Cache cache = super.getCache(name);
//		if (cache == null) {
//			cache = new MemcachedCache(name, maxElements, expire, client);
//			super.addCache(cache);
//		}
//		return cache;
//	}
//
//	public void setMaxElements(int maxElements) {
//		this.maxElements = maxElements;
//	}
//
//	public void setExpire(int expire) {
//		this.expire = expire;
//	}
//
//}
