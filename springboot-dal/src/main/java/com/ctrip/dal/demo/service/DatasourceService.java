package com.ctrip.dal.demo.service;

import com.ctrip.dal.demo.util.FunctionWrapUtil;
import com.ctrip.datasource.configure.DalDataSourceFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class DatasourceService {

    private DalDataSourceFactory dalDataSourceFactory;

    public DatasourceService() {
        this.dalDataSourceFactory = new DalDataSourceFactory();
    }

    public Connection getConnection(String clusterName) {
        return FunctionWrapUtil.wrapWithRuntimeException(() -> dalDataSourceFactory.getOrCreateDataSource(clusterName).getConnection());
    }
}
