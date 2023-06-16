package com.pictet.technologies.ezzine.fileorchestrator.controller;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	private static final String HELLO = "Hello!";

	@GetMapping(path = "/hello")
	public String helloWorld(@RequestParam String name) {
//		var result = StringUtils.isNotBlank(name) 
//				? "Hello, " + name + "!" 
//						: "Hello!";
//
//		var result2 = Optional.ofNullable(name)
//				// .filter(it -> StringUtils.isNotBlank(it))
//				.filter(StringUtils::isNotBlank)
//				.map(it -> "Hello, " + name + "!")
//				.orElse("Hello!");

		return buildHelloMessage(name);
	}

	@PostMapping(path = "/hello")
	public String text(@RequestBody(required = false) ParametreDTO parametre) {
//		if (parametre != null) {
//			return buildHelloMessage(parametre.name());
//		}
//		return HELLO;
		
		return Optional.ofNullable(parametre)
			.map(ParametreDTO::name)
			.map(name -> buildHelloMessage(name))
			.orElse(HELLO);
	}
	
	
	private String buildHelloMessage(String name) {
		if (StringUtils.isNotBlank(name)) {
			return "Hello, " + name + "!";
		} else {
			return HELLO;
		}
	}
}
