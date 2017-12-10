package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHai {

	@RequestMapping("/hai")
	public String hai() {
		return "hai, how are you! are you ok?";
	}
}
