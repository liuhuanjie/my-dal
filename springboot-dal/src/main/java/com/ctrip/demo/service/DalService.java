package com.ctrip.demo.service;


import com.ctrip.demo.entity.Dalservicetable;
import com.ctrip.demo.util.FunctionWrapUtil;
import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.base.DalDatabaseOperations;
import com.ctrip.platform.dal.dao.base.DalTableOperations;
import com.ctrip.platform.dal.dao.base.SQLResult;
import com.ctrip.platform.dal.dao.base.SQLResultSpec;
import com.ctrip.platform.dal.dao.client.DalOperationsFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ctrip.demo.util.FunctionWrapUtil.*;

@Service
public class DalService {

    private final Class clazz = Dalservicetable.class;
    private final String logic_db_name = "dbadalclustertest01db_dalcluster";

    private DalTableOperations<Dalservicetable> dalTableOperations;
    private DalDatabaseOperations dalDatabaseOperations;


    public DalService() {
        dalTableOperations = DalOperationsFactory.getDalTableOperations(Dalservicetable.class);
        dalDatabaseOperations = DalOperationsFactory.getDalDatabaseOperations(logic_db_name);
    }

    public <T> List<T> queryBySql(String sql, Class<T> clazz, DalHints dalHints, Object... params) {
        return wrapWithRuntimeException(() -> dalDatabaseOperations.query(sql, dalHints, SQLResult.type(clazz), params));
    }
}
