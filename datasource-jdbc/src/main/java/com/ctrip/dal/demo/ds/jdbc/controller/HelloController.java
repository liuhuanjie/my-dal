package com.ctrip.dal.demo.ds.jdbc.controller;


import com.ctrip.datasource.configure.DalDataSourceFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
@RequestMapping("hello")
public class HelloController {

    private DalDataSourceFactory factory = new DalDataSourceFactory();

    @GetMapping(value = "/get/datasource")
    public String getDatasource() throws Exception {
        DataSource dbaDatasource = factory.getOrCreateDataSource("dbadalclustertest01db_dalcluster");
        try (Connection connection = dbaDatasource.getConnection()) {
            return connection.getMetaData().getURL();
        }catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
