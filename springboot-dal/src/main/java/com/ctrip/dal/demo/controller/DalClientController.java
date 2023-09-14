package com.ctrip.dal.demo.controller;

import com.ctrip.dal.demo.entity.CacheCluster;
import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.DalQueryDao;
import com.ctrip.platform.dal.dao.StatementParameters;
import com.ctrip.platform.dal.dao.base.DalTableOperations;
import com.ctrip.platform.dal.dao.client.DalOperationsFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@RestController
public class DalClientController {

    private DalTableOperations<CacheCluster> tableOperations;
    private DalQueryDao queryDao;

    @PostConstruct
    public void init() {
        tableOperations = DalOperationsFactory.getDalTableOperations(CacheCluster.class);
        queryDao = new DalQueryDao("dbadalclustertest01db_dalcluster");

    }

    @GetMapping(value = "get/table")
    public String getTable() throws SQLException {
        StatementParameters parameters = new StatementParameters();
        parameters.set(1, "ID", Types.INTEGER, 12395);
        List<CacheCluster> query = queryDao.query("select * from cache_cluster where ID = ? AND IsDeleted=0", parameters, new DalHints().allowPartial(), CacheCluster.class);
        CacheCluster cluster = query.get(0);
        System.out.println(cluster);
        return cluster.toString();
    }


}
