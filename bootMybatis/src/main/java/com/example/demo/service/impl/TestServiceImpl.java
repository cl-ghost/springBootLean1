package com.example.demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.demo.dao.Dao;
import com.example.demo.db.annotation.Master;
import com.example.demo.db.annotation.Slave;
import com.example.demo.pojo.Test;
import com.example.demo.service.inter.TestService;

@Service
public class TestServiceImpl implements TestService {
	@Resource
	Dao dao;
	
	
	@Master
	public void selectTest() {
		// TODO Auto-generated method stub
		Test test = new Test();
		test.setId("zy");
		Test b = (Test) this.dao.selectOne(test);
		System.out.println(b.getValue());
		
		
		Test c = (Test) this.dao.findOne(test);
		System.out.println(c.getValue());
	}
	
	@Slave
	public void selectTest2() {
		Test test = new Test();
		test.setId("zy");
		Test b = (Test) this.dao.selectOne(test);
		System.out.println(b.getValue());
	}

}
