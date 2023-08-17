package com.ctrip.demo.controller;

import com.ctrip.datasource.configure.DalDataSourceFactory;
import com.ctrip.demo.entity.Dalservicetable;
import com.ctrip.demo.util.RandomGenerator;
import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.DalTableDao;
import com.ctrip.platform.dal.dao.base.DalTableOperations;
import com.ctrip.platform.dal.dao.client.DalOperationsFactory;
import com.ctrip.platform.dal.dao.helper.DalServiceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController(value = "/hello")
public class HelloController {

    private DalDataSourceFactory factory = new DalDataSourceFactory();
    private DalTableOperations<Dalservicetable> tableOperations ;

    @PostConstruct
    private void init() throws SQLException {
        tableOperations = new DalTableDao<>(Dalservicetable.class, "dbatestdbapplydb_dalcluster");
    }

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

    @GetMapping(value = "/insert/ob")
    public int insertObPojo() throws SQLException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        System.out.println("insert: " + formatter.format(new Date()));
        return tableOperations.insert(new DalHints(), RandomGenerator.getTableInstance());
    }

}