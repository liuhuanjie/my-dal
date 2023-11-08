package com.ctrip.dal.demo.service;


import com.ctrip.dal.demo.entity.User;
import com.ctrip.dal.demo.util.FunctionWrapUtil;
import com.ctrip.dal.demo.entity.Dalservicetable;
import com.ctrip.dal.demo.util.RandomGenerator;
import com.ctrip.framework.dal.cluster.client.cluster.ArchiveStrategyEnum;
import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.annotation.DalTransactional;
import com.ctrip.platform.dal.dao.base.DalDatabaseOperations;
import com.ctrip.platform.dal.dao.base.DalTableOperations;
import com.ctrip.platform.dal.dao.base.SQLResult;
import com.ctrip.platform.dal.dao.client.DalOperationsFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DalService {

    private final Class clazz = Dalservicetable.class;
    private final String logic_db_name = "dbadalclustertest01db_dalcluster";
    private final String name = "{\"id\":1, \"name\":\"name\", \"age\": 13}";

    private DalTableOperations<Dalservicetable> dalTableOperations;
    private DalDatabaseOperations dalDatabaseOperations;


    public DalService() {
//        dalTableOperations = DalOperationsFactory.getDalTableOperations(Dalservicetable.class);
        dalDatabaseOperations = DalOperationsFactory.getDalDatabaseOperations("fxdaltestdb_dalcluster");
    }

    public <T> List<T> queryBySql(String sql, Class<T> clazz, DalHints dalHints, Object... params) {
        return FunctionWrapUtil.wrapWithRuntimeException(() -> dalDatabaseOperations.query(sql, dalHints, SQLResult.type(clazz), params));
    }

    @DalTransactional(logicDbName = "fxdaltestdb_dalcluster")
    public void transactionMethod(DalHints dalHints) throws SQLException {
        System.out.println(dalDatabaseOperations.query("select * from user where id = ?", new DalHints(), SQLResult.type(User.class),  1));
        String name = RandomGenerator.getRandomString(5);
        System.out.println(name);
        dalDatabaseOperations.update("update user set name = ? where id = ?", new DalHints(), SQLResult.type(User.class), name, 1);
        List<User> query = dalDatabaseOperations.query("select * from user where id = ?", new DalHints(), SQLResult.type(User.class),  1);
        System.out.println(query);
        dalDatabaseOperations.update("update user set name = ? where id = ?", new DalHints(), SQLResult.type(User.class), name, 1);
        System.out.println(dalDatabaseOperations.query("select * from user where id = ?", new DalHints(), SQLResult.type(User.class),  1));

    }
}
