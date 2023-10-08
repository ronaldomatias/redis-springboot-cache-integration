package com.tcclibrary.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class RedisDTO {
	private String key;
	private Object value;
	private Long ttl;

	public RedisDTO(String key, Long ttl) {
		this.key = key;
		this.ttl = ttl;
	}

}