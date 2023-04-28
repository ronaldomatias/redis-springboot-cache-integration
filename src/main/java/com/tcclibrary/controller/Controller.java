package com.tcclibrary.controller;

import com.tcclibrary.service.PessoaService;
import com.tcclibrary.component.redis.RedisBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {
	private RedisBase redisBase;
	@Autowired
	private PessoaService pessoaService;

	@PostMapping
	public String getPessoa() {
		return pessoaService.getPessoa();
	}

}
