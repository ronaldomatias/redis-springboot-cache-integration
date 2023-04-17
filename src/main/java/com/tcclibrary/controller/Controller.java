package com.tcclibrary.controller;

import com.tcclibrary.annotation.Cacheable;
import com.tcclibrary.component.redis.RedisBase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
	private RedisBase redisBase;

	@PostMapping
	@Cacheable(ttl = "15000")
	public String getCategories(){
		return redisBase.get("");
	}

}
