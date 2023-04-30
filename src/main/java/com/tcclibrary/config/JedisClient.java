package com.tcclibrary.config;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class JedisClient {
	private static String host = "localhost";
	private static Integer port = 6379;
	private static final Jedis jedis = new Jedis(host, port);

	public JedisClient() {
	}

	public static Jedis getJedis() {
		return jedis;
	}
}
