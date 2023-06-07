package com.tcclibrary.jedis;

import redis.clients.jedis.Jedis;

public class JedisClient {
	private Jedis jedis;

	public JedisClient(String host, Integer port) {
		this.jedis = new Jedis(host, port);
	}

	public Jedis getJedis() {
		return jedis;
	}

}
