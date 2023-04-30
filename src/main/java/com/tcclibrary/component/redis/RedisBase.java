package com.tcclibrary.component.redis;

import com.tcclibrary.config.JedisClient;
import com.tcclibrary.util.ObjectMapperUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;


@Component
public class RedisBase implements RedisInterface {
	JedisClient client = new JedisClient();

	@Override
	@SneakyThrows
	public void set(RedisDTO dto) {
		client.getJedis()
				.set(dto.getKey(), ObjectMapperUtil.getObjectMapper().writeValueAsString(dto.getValue()));

		client.getJedis().expire(dto.getKey(), dto.getTtl());
	}

	@Override
	public String get(String key) {
		return client.getJedis().get(key);
	}

	@Override
	public boolean existsKey(String key) {
		return client.getJedis().exists(key);
	}


}
