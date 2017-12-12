package com.example.demo.db.datasource;

import com.example.demo.until.Until.DataSourceType;

public class DataSourceTypeManager {
	private static final ThreadLocal<DataSourceType> dataSourceTypes = new ThreadLocal<DataSourceType>() {
		@Override
		protected DataSourceType initialValue() {
			return DataSourceType.MASTER;
		}
	};

	public static DataSourceType get() {
		return dataSourceTypes.get();
	}

	public static void set(DataSourceType dataSourceType) {
		dataSourceTypes.set(dataSourceType);
	}

	public static void reset() {
		dataSourceTypes.set(DataSourceType.MASTER);
	}
}
