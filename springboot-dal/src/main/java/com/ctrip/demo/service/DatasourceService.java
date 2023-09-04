package com.ctrip.demo.service;

import com.ctrip.datasource.configure.DalDataSourceFactory;
import com.ctrip.demo.util.FunctionWrapUtil;
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
