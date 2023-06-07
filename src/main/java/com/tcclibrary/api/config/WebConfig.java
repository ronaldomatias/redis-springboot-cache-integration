package com.tcclibrary.api.config;

import com.tcclibrary.jedis.JedisClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	@Bean
	public JedisClient beanJedisClientConfiguration() {
		return new JedisClient("localhost", 6379);
	}

}
