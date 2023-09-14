package com.ctrip.datasource.demo.controller;


import com.ctrip.dal.demo.entity.Dalservicetable;
import com.ctrip.datasource.demo.service.MybatisPlusService;
import com.ctrip.datasource.demo.service.RawJdbcService;
import com.ctrip.platform.dal.dao.helper.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.sql.SQLException;

@Controller
public class HelloController {

    @Resource
    private RawJdbcService rawJdbcService;

    @Resource
    private MybatisPlusService mybatisPlusService;

    @GetMapping(value = "raw/query")
    public String rawQuery(@RequestParam(name = "type")String type,
                           @RequestParam(name = "sql")String sql,
                           @RequestParam(name = "params", required = false)String[] params) throws SQLException {
        if (type.equalsIgnoreCase("statement")) {
            return rawJdbcService.queryByStatement(sql);
        }
        return rawJdbcService.queryByPreStatement(sql, params);
    }

    @GetMapping(value = "operate/entity")
    public String operateEntity(@RequestParam(name = "id", required = false)Long id,
                              @RequestParam(name = "name", required = false)String name,
                              @RequestParam(name = "age", required = false)Integer age,
                                @RequestParam(name = "type")String type) {
        Dalservicetable dalservicetable = new Dalservicetable().setId(id).setName(name).setAge(age);
        switch (type) {
            case "insert":
                mybatisPlusService.insert(dalservicetable);
                break;
            case "service2insert":
                    mybatisPlusService.service2insert(dalservicetable);
            case "update":
                mybatisPlusService.update(dalservicetable);
            default:
                return "success";
        }
        return  "success";
    }
}
