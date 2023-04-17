package com.tcclibrary.component.redis;

import com.tcclibrary.interceptor.CacheInterceptor.RedisDTO;
import org.springframework.stereotype.Component;

@Component
public class RedisBase implements RedisInterface {

	@Override
	public void set(String key, RedisDTO value, Long ttl) {

	}

	@Override
	public String get(String key) {
		return "Value";
	}

	@Override
	public Long getTtl(String key) {
		return 210421L;
	}

	@Override
	public boolean expiredTTL(RedisDTO redisDTO) {
		return this.getTtl(redisDTO.getKey()) > redisDTO.getTtl();
	}

	public boolean existsKey(String key) {
		return this.get(key) != null;
	}

	public void saveOrUpdateValueInRedis(RedisDTO redisDTO) {
		if (!existsKey(redisDTO.getKey())) {
			this.set(redisDTO.getKey(), redisDTO, redisDTO.getTtl());
		} else {
			if (expiredTTL(redisDTO)) {
				this.set(redisDTO.getKey(), redisDTO, redisDTO.getTtl());
			}
		}
	}


}
