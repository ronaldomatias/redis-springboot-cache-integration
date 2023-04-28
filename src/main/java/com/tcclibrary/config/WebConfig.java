package com.tcclibrary.config;

import com.tcclibrary.component.redis.RedisBase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Bean
	public RedisBase beanRedisBase() {return new RedisBase();
	}

}
