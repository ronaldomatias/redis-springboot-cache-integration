package com.tcclibrary.service;

import com.tcclibrary.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

	@Cacheable(key = "pessoa2", ttl = 10, type = PessoaService.class)
	public String getPessoa() {
		return "Ronaldo";
	}

}
