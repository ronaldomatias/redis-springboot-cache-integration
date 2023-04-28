package com.tcclibrary.component.redis;

import com.tcclibrary.util.ObjectMapperUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;


@Component
public class RedisBase implements RedisInterface {
	JedisClient jedisClient = new JedisClient();

	@Override
	@SneakyThrows
	public void set(RedisDTO dto) {
		jedisClient.getJedis().set(dto.getKey(), ObjectMapperUtil.getObjectMapper().writeValueAsString(dto.getValue()));
	}

	@Override
	public String get(String key) {
		return jedisClient.getJedis().get(key);
	}

	@Override
	public boolean existsKey(String key) {
		return jedisClient.getJedis().exists(key);
	}


}
