package com.example.demo.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.Dao;
import com.example.demo.db.annotation.Master;
import com.example.demo.db.annotation.Slave;
import com.example.demo.until.Until.DataSourceType;


@Repository
public class DaoImpl<T> implements Dao<T> {
	@Autowired
	SqlSessionFactory sqlSessionFactory;

	@Override
	public boolean insert(Object obj) {
		return sqlSessionFactory.openSession().insert(getOName(obj) + ".insert", obj) > 0;
	}

	@Override
	public boolean insert(Object obj, String table) {
		return sqlSessionFactory.openSession().insert(getOName(obj) + "." + table, obj) > 0;
	}

	@Override
	public boolean insert(Map<String, Object> search) {
		return sqlSessionFactory.openSession().insert(search.get("table") + ".insert", search) > 0;
	}

	@Override
	public boolean insert(Map<String, Object> search, String table) {
		return sqlSessionFactory.openSession().insert(search.get("table") + "." + table, search) > 0;
	}

	@Override
	public boolean delete(Object obj) {
		return sqlSessionFactory.openSession().delete(getOName(obj) + ".delete", obj) > 0;
	}

	@Override
	public boolean delete(Object obj, String table) {
		return sqlSessionFactory.openSession().delete(getOName(obj) + "." + table, obj) > 0;
	}

	@Override
	public boolean delete(Map<String, Object> search) {
		return sqlSessionFactory.openSession().delete(search.get("table") + ".delete", search) > 0;
	}

	@Override
	public boolean delete(Map<String, Object> search, String table) {
		return sqlSessionFactory.openSession().delete(search.get("table") + "." + table, search) > 0;
	}

	@Override
	public boolean update(Object obj) {
		return sqlSessionFactory.openSession().update(getOName(obj) + ".update", obj) > 0;
	}

	@Override
	public boolean update(Object obj, String table) {
		return sqlSessionFactory.openSession().update(getOName(obj) + "." + table, obj) > 0;

	}

	@Override
	public boolean update(Map<String, Object> search) {
		return sqlSessionFactory.openSession().update(search.get("table") + ".update", search) > 0;
	}

	@Override
	public boolean update(Map<String, Object> search, String table) {
		return sqlSessionFactory.openSession().update(search.get("table") + "." + table, search) > 0;
	}
	@Override
	@Slave
	public List<T> list(Object obj) {
		return sqlSessionFactory.openSession().selectList(getOName(obj) + ".list" , obj);
	}
	@Override
	public List<T> list(Object obj, String table) {
		return sqlSessionFactory.openSession().selectList(getOName(obj) + "."+table , obj);
	}

	@Override
	public List<T> list(Map<String, Object> search) {
		return sqlSessionFactory.openSession().selectList(search.get("table") + ".list" , search);
	}

	@Override
	public List<T> list(Map<String, Object> search, String table) {
		return sqlSessionFactory.openSession().selectList(search.get("table") + "."+table , search);
	}

	@Override
	public T selectOne(Object obj) {
		return sqlSessionFactory.openSession().selectOne(getOName(obj) + ".find" , obj);
	}
 

	@Override
	public T selectOne(Object obj, String table) {
		return sqlSessionFactory.openSession().selectOne(getOName(obj) + "."+table , obj);
	}

	@Override
	@Master
	public T selectOne(Map<String, Object> search) {
		return sqlSessionFactory.openSession().selectOne(search.get("table") + ".fing" , search);
	}

	@Override
	public T selectOne(Map<String, Object> search, String table) {
		return sqlSessionFactory.openSession().selectOne(search.get("table") + "."+table , search);
	}

	private String getOName(Object obj) {
		String str = obj.getClass().getName();
		return str.substring(str.lastIndexOf(".") + 1);
	}

	@Override
	@Slave
	public T findOne(Object obj) {
		return sqlSessionFactory.openSession().selectOne(getOName(obj) + ".find" , obj);
	}
}
