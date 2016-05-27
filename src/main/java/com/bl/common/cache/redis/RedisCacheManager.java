package com.bl.common.cache.redis;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractCacheManager;

import redis.clients.jedis.JedisCluster;



public class RedisCacheManager extends AbstractCacheManager {

	private JedisCluster client;
	
	// 默认过期时间5分钟,单位秒
	private Integer expire ;
	
	
	public void setClient( JedisCluster client) {
		this.client = client;
	}
	

	public void setExpire(int expire) {
		this.expire = expire;
	}

	// AbstractCacheManager.afterPropertiesSet不允许loadCaches返回空，所以覆盖掉
	@Override
	public void afterPropertiesSet() {
	}

	@Override
	protected Collection<? extends Cache> loadCaches() {
		// TODO Auto-generated method stub
		return null;
	}
	// 根据名称获取cache，对应注解里的value如notice_cache，没有就创建并加入cache管理
	public Cache getCache(String name) {
		Cache cache = super.getCache(name);
		if (cache == null) {
			cache = new RedisCache(name,expire, client);
			super.addCache(cache);
		}
		return cache;
	}
	
}
