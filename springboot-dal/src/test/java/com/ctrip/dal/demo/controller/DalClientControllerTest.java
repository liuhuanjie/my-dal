package com.ctrip.dal.demo.controller;

import com.ctrip.dal.demo.entity.CacheCluster;
import com.ctrip.dal.demo.entity.Dalservicetable;
import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.DalQueryDao;
import com.ctrip.platform.dal.dao.StatementParameters;
import com.ctrip.platform.dal.dao.base.DalDatabaseOperations;
import com.ctrip.platform.dal.dao.base.DalTableOperations;
import com.ctrip.platform.dal.dao.base.SQLResult;
import com.ctrip.platform.dal.dao.client.DalOperationsFactory;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class DalClientControllerTest {

    @Test
    public void queryDaoAllowPartial() throws SQLException {
        DalQueryDao queryDao = new DalQueryDao("fxcachedb_dalcluster");
        StatementParameters parameters = new StatementParameters();
        String sql = "select * from cache_cluster where ID = ? AND IsDeleted=0";
        parameters.set(1, "ID", Types.INTEGER, 261);
        System.out.println(queryDao.queryForObjectNullable(sql, parameters, new DalHints().allowPartial(), CacheCluster.class).getUsers());
    }

    @Test
    public void databaseOperationsAllowPartial() throws SQLException {
        DalDatabaseOperations operations = DalOperationsFactory.getDalDatabaseOperations("fxcachedb_dalcluster");
        String sql = "select * from cache_cluster where ID = ? AND IsDeleted=0";
        System.out.println(operations.query(sql, new DalHints().allowPartial(), SQLResult.type(CacheCluster.class), 261));
    }

    @Test
    public void batchInsertTest() throws SQLException {
        DalTableOperations<Dalservicetable> operations = DalOperationsFactory.getDalTableOperations(Dalservicetable.class, "dbadalclustertest01db_dalcluster", "daltablenotnull");
        List<Dalservicetable> dalservicetables = Lists.newArrayList(
                new Dalservicetable().setId(1L).setName("name"),
                new Dalservicetable().setId(2L).setName("name"),
                new Dalservicetable().setId(3L).setName("name"),
                new Dalservicetable().setId(4L).setName("name")
        );
        operations.batchInsert(new DalHints(), dalservicetables);
    }

}