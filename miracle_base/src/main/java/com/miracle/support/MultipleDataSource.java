package com.miracle.support;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/*
 * Created by yangchao2014 on 2016/11/29.
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
  @Override
  protected Object determineCurrentLookupKey() {
    return DataSourceContextHolder.getDataSourceType();
  }
}
