package com.ctrip.dal.demo.controller;

import com.ctrip.dal.demo.entity.CacheCluster;
import com.ctrip.dal.demo.entity.Dalservicetable;
import com.ctrip.dal.demo.entity.User;
import com.ctrip.dal.demo.util.RandomGenerator;
import com.ctrip.datasource.configure.DalDataSourceFactory;
import com.ctrip.framework.dal.cluster.client.cluster.ArchiveStrategyEnum;
import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.DalQueryDao;
import com.ctrip.platform.dal.dao.StatementParameters;
import com.ctrip.platform.dal.dao.base.DalDatabaseOperations;
import com.ctrip.platform.dal.dao.base.DalTableOperations;
import com.ctrip.platform.dal.dao.base.SQLResult;
import com.ctrip.platform.dal.dao.client.DalOperationsFactory;
import com.google.common.collect.Lists;
import com.mysql.cj.xdevapi.SqlResult;
import org.junit.Test;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
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

    @Test
    public void mirrorExecuteTest() throws SQLException {
        DalTableOperations<User> operations = DalOperationsFactory.getDalTableOperations(User.class);
        User user = generateUser(1L, "");
        DalHints dalHints = new DalHints().archiveStrategy(ArchiveStrategyEnum.ONLINE_FIRST);
        System.out.println(operations.queryByPk(user, dalHints));

        String name = RandomGenerator.getRandomString(5);

        user.setName(name);
        DalHints archiveHints = new DalHints().archiveStrategy(ArchiveStrategyEnum.ARCHIVE_ONLY);
        operations.update(archiveHints, user);
        System.out.println(operations.queryByPk(user, archiveHints));

        name = RandomGenerator.getRandomString(5);
        user.setName(name);
        operations.update(dalHints, user);
        System.out.println(operations.queryByPk(user, dalHints));

        name = RandomGenerator.getRandomString(5);
        user.setId(7L).setName(name);
        operations.insert(archiveHints, user);
        System.out.println(operations.queryByPk(user, archiveHints));

        name = RandomGenerator.getRandomString(5);
        user.setName(name);
        operations.insert(dalHints, user);
        System.out.println(operations.queryByPk(user, dalHints));
    }

    @Test
    public void resultTest() throws Exception {
        DataSource dataSource = new DalDataSourceFactory().getOrCreateDataSource("dbadalclustertest01db_dalcluster");
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from dalservicetable where id < 197054580;");
            ResultSetMetaData metaData = resultSet.getMetaData();
            System.out.println(metaData);
        } catch (Exception e) {

        }

    }

    @Test
    public void noMirrorSelectTest() throws SQLException {
        DalTableOperations<Dalservicetable> tableOperations = DalOperationsFactory.getDalTableOperations(Dalservicetable.class);

        System.out.println(tableOperations.queryByPk(new Dalservicetable().setId(1L), new DalHints()));
    }

    @Test
    public void mirrorDataSourceTest() throws Exception {
        DataSource dataSource = new DalDataSourceFactory().getOrCreateDataSource("dbadalclustertest01db_dalcluster");
        System.out.println(dataSource);
    }

    @Test
    public void mirrorUpdateTest() throws SQLException {
        long id = 1L;
        String name = "user" + id;
        DalTableOperations<User> operations = DalOperationsFactory.getDalTableOperations(User.class);
        User user = generateUser(id, name);

        System.out.println(operations.update(new DalHints(), user));
    }

    private User generateUser(long id, String userName) {
        User user = new User();
        user.setId(id);
        user.setName(userName);
        user.setAge((int) id);

        return user;
    }

    @Test
    public void mirrorInsertTest() throws SQLException {
        long id = 1L;
        String name = "user" + id;
        DalTableOperations<User> operations = DalOperationsFactory.getDalTableOperations(User.class);
        User user = generateUser(id, name);

        System.out.println(operations.insert(new DalHints(), user));
    }

    @Test
    public void printTest() {
        User user = new User();
        user.setId(1L);
        user.setName("name");
        user.setAge(1);
        System.out.println(user);
    }

}