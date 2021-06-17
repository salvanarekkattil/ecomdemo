package com.demo.ecom.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CachingService {

	@Autowired
	CacheManager cacheManager;

	public <T> void putToCache(String cacheName, String key, T value) {
		cacheManager.getCache(cacheName).put(key, value);
	}

	public <T> T getFromCache(String cacheName, String key, Class<T> objType) {
		T value = null;
		if (cacheManager.getCache(cacheName).get(key) != null) {
			value = (T)cacheManager.getCache(cacheName).get(key, objType);
		}
		return value;
	}

	public void evictSingleCacheValue(String cacheName, String cacheKey) {
		cacheManager.getCache(cacheName).evict(cacheKey);
	}
}
