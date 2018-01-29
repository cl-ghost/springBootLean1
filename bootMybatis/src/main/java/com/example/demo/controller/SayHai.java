package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.inter.TestService;

@RestController
public class SayHai {

	
	@Autowired
	TestService tester;
	
	@RequestMapping("/hai")
	public String hai() {
		tester.selectTest();
		return "hai, how are you! are you ok?";
	}
	
	@RequestMapping("/haha")
	public String haha() {
			tester.selectTest();
			return "hai, how are you! are you ok? hah ";
	}
}
