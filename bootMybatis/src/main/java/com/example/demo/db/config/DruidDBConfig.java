package com.example.demo.db.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.aspectj.apache.bcel.Repository;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.example.demo.db.datasource.ThreadLocalRountingDataSource;
import com.example.demo.until.Until.DataSourceType;

/**
 * druid db config
 * @author cl
 *
 */
@Configuration
@EnableTransactionManagement
public class DruidDBConfig {

	@Value("${druid.type}")
	private Class<? extends DataSource> dataSourceType;
	
	//******************** 数据源配置 start  *****************************
	@Bean(name = "master")
	@ConfigurationProperties(prefix = "druid.db.master")
	public DataSource masterDataSource() {
		return DataSourceBuilder.create().type(dataSourceType).build();
	}
	
	
	@Bean(name = "slave")
	@ConfigurationProperties(prefix = "druid.db.slave")
	public DataSource slaveDataSource() {
		return DataSourceBuilder.create().type(dataSourceType).build();
	}
	
	//****************** 数据源配置 end ***********************************
	
	@Bean(name = "stat-filter")
	public StatFilter druidStateFilter() {
		StatFilter filter = new StatFilter();
		filter.setMergeSql(true);
		filter.setSlowSqlMillis(2000);
		filter.setLogSlowSql(true);
		return filter;
	}
	
	@Bean(name="druid-stat-interceptor")
	public DruidStatInterceptor druidDruidStatInterceptor() {
		DruidStatInterceptor interceptor = new DruidStatInterceptor();
		return interceptor;
	}
	
	@Bean(name="druid-stat-pointcut")
	public JdkRegexpMethodPointcut druidJdkRegexpMethodPointcut() {
		JdkRegexpMethodPointcut jdkReg = new JdkRegexpMethodPointcut();
		jdkReg.setPatterns("com.example.demo.service.*","com.example.demo.db.*");
		return jdkReg;
	}
	
	
	//**************** 数据源配置
	@Bean(name="dataSource")
	@Primary
	public AbstractRoutingDataSource druidThreadLocLRountingDataSource() {
		ThreadLocalRountingDataSource threadDataSource = new ThreadLocalRountingDataSource();
		 Map<Object, Object> targetDataResources = new HashMap<>();
		 targetDataResources.put(DataSourceType.MASTER, masterDataSource());
		 targetDataResources.put(DataSourceType.SLAVE, slaveDataSource());
		 threadDataSource.setDefaultTargetDataSource(masterDataSource());
		 threadDataSource.setTargetDataSources(targetDataResources);
		 threadDataSource.afterPropertiesSet();
		 return threadDataSource;
	}
	
	
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sqlSession = new SqlSessionFactoryBean();
		sqlSession.setDataSource(druidThreadLocLRountingDataSource());
		sqlSession.setTypeAliasesPackage("com.example.demo");
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			sqlSession.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession;
	}
	
	public MapperScannerConfigurer mapperScannerConfig() {
		MapperScannerConfigurer mapperScanner = new MapperScannerConfigurer();
		mapperScanner.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScanner.setBasePackage("com.example.demo");
		return mapperScanner;
	}
	
}
