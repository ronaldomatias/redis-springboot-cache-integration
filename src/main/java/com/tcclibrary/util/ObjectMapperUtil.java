package com.tcclibrary.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static ObjectMapper getObjectMapper(){
		return objectMapper;
	}



}
