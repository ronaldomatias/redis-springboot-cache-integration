package com.tcclibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RedisDTO {
	private String key;
	private Object value;
	private Long ttl;

	public RedisDTO(String key, Object value) {
		this.key = key;
		this.value = value;
	}

	public RedisDTO(String key) {
		this.key = key;
	}
}