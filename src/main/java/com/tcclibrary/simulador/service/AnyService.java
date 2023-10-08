package com.tcclibrary.simulador.service;

import com.tcclibrary.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AnyService {

	@Cacheable(key = "categories", ttl = 300)
	public List<String> getCategories() {
		return Arrays.asList("Controlados", "Alimentos", "Genericos");
	}

}
