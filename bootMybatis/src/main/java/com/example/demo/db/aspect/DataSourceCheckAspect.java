package com.example.demo.db.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.example.demo.db.datasource.DataSourceTypeManager;
import com.example.demo.until.Until.DataSourceType;

@Aspect
@Component
public class DataSourceCheckAspect implements Ordered {

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Before("@annotation(com.example.demo.db.annotation.Master)")
	public void choseMastDataSource(JoinPoint joinPoint) throws Exception {
		DataSourceTypeManager.set(DataSourceType.MASTER);
	}
	
	@Before("@annotation(com.example.demo.db.annotation.Slave)")
	public void choseSlaveDataSource(JoinPoint joinPoint) throws Exception {
		DataSourceTypeManager.set(DataSourceType.SLAVE);
	}
}
