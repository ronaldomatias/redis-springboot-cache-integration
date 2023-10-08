package com.tcclibrary.simulador.controller;

import com.tcclibrary.simulador.service.AnyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnyController {
	@Autowired
	private AnyService anyService;

	@GetMapping
	public List<String> getCategories() {
		return anyService.getCategories();
	}

}
