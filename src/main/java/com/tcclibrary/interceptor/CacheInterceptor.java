package com.tcclibrary.interceptor;

import com.tcclibrary.annotation.Cacheable;
import com.tcclibrary.component.redis.RedisBase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CacheInterceptor implements HandlerInterceptor {
	private RedisBase redisBase;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		if (!(handler instanceof HandlerMethod) || ((HandlerMethod) handler).getBeanType().equals(BasicErrorController.class)) {
			return true;
		}


		if (((HandlerMethod) handler).getMethod().isAnnotationPresent(Cacheable.class)) {
			RedisDTO redisDTO = new RedisDTO("key", "value", String.class);
			redisBase.get(redisDTO.getKey());

			return true;
		}

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) {
		String key = "{'branchId': '2'}";
		Object value = "{'name': 'IVERMECTINA 16MG'}";

		String ttl =  ((HandlerMethod) handler).getMethod().getAnnotation(Cacheable.class).ttl();

		redisBase.saveOrUpdateValueInRedis(new RedisDTO(key, value, String.class));
	}





	@Data
	@AllArgsConstructor
	public static class RedisDTO {
		private String key;
		private Object value;
		private Class type;
		private Long ttl;

		public RedisDTO(String key, Object value, Class type) {
			this.key = key;
			this.value = value;
			this.type = type;
		}
	}

}
