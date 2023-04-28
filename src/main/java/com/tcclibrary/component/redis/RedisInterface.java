package com.tcclibrary.component.redis;


public interface RedisInterface {
	void set(RedisDTO value);

	String get(String key);

	boolean existsKey(String key);
}
