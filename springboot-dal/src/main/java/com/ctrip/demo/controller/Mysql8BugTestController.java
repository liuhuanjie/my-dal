package com.ctrip.demo.controller;


import com.ctrip.demo.enums.MysqlInstanceEnum;
import com.ctrip.demo.service.DalService;
import com.ctrip.demo.service.DatasourceService;
import com.ctrip.demo.service.DriverService;
import com.ctrip.demo.util.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.*;

@RestController
public class Mysql8BugTestController {

    @Resource
    private DriverService driverService;

    @Resource
    private DatasourceService datasourceService;

    @Resource
    private DalService dalService;


    @GetMapping("/timeout/npe")
    public String timeoutNpe(@RequestParam(name = "type") String type) {
        switch (type) {
            case "prepare":
                try(Connection connection = driverService.getConnection(MysqlInstanceEnum.dalclustertest01_slave)) {
                    PreparedStatement statement = connection.prepareStatement("select * from dalservicetable limit 10");
                    statement.setQueryTimeout(2);
                    statement.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "statement":
                try(Connection connection = driverService.getConnection(MysqlInstanceEnum.dalclustertest01_slave)) {
                    Statement statement = connection.createStatement();
                    statement.setQueryTimeout(2);
                    statement.executeQuery("select * from dalservicetable limit 10");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        return "";
    }

}
