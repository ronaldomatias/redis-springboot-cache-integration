package com.tcclibrary.service;

import com.tcclibrary.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

	@Cacheable(key = "pessoa", ttl = 15000, type = PessoaService.class, method = "getPessoa")
	public String getPessoa() {
		return "Ronaldo";
	}

}
