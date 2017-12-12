package com.example.demo.db.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.example.demo.until.Until.DataSourceType;

public class ThreadLocalRountingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceTypeManager.get();
	}

}
