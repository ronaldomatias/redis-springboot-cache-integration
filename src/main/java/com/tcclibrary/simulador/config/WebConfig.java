package com.tcclibrary.simulador.config;

import com.tcclibrary.jedis.JedisClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

	// TODO: O sistema deve receber o host e port do properties.

	@Bean
	public JedisClient jedisClient() {
		return new JedisClient("localhost", 6379);
	}

}
