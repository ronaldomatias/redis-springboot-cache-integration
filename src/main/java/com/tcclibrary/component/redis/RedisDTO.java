package com.tcclibrary.component.redis;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RedisDTO {
	private String key;
	private Object value;
	private Class type;

	private Long ttl;

	public RedisDTO(String key, Object value, Class type) {
		this.key = key;
		this.value = value;
		this.type = type;
	}

	public RedisDTO(String key) {
		this.key = key;
	}
}