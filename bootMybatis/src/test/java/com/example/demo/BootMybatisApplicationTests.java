package com.example.demo;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.service.inter.TestService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootMybatisApplicationTests {

	@Resource
	TestService testSer;
	
	@Test
	public void contextLoads() {
		testSer.selectTest();
	}

}
