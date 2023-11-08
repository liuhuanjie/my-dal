package com.ctrip.dal.demo.service;

import com.ctrip.dal.demo.enums.MysqlInstanceEnum;
import com.ctrip.dal.demo.util.Constants;
import com.ctrip.dal.demo.util.FunctionWrapUtil;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

@Service
public class DriverService {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception t) {
                throw new RuntimeException(t);
            }
        }
    }

    public Connection getConnection(MysqlInstanceEnum mysqlInstanceEnum) {
        return FunctionWrapUtil.wrapWithRuntimeException(() -> DriverManager.getConnection(String.format(Constants.MYSQL_URL_FORMAT, mysqlInstanceEnum.ipAndPort, mysqlInstanceEnum.dbname), mysqlInstanceEnum.uid, mysqlInstanceEnum.pwd));
    }

}
