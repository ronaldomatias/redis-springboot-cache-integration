package com.tcclibrary.config;

import org.springframework.beans.factory.annotation.Value;

public class RedissonConfig {

	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private String redisPort;

	@Value("${spring.redis.database}")
	private Integer redisDatabase;

	@Value("${spring.redis.password}")
	private String redisPassword;

}