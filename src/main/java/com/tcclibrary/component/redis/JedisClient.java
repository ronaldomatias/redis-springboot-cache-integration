package com.tcclibrary.component.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class JedisClient {
	private String host = "localhost";
	private Integer port = 6379;
	private final Jedis jedis;

	public JedisClient() {
		this.jedis = new Jedis(host, port);
	}

	public Jedis getJedis() {
		return jedis;
	}
}
