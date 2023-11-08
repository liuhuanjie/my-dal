package com.ctrip.datasource.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ctrip.dal.demo.entity.Dalservicetable;
import com.ctrip.datasource.demo.mapper.dbadalclustertest01db.DbaDalClusterServiceTableMapper;
import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.base.DalDatabaseOperations;
import com.ctrip.platform.dal.dao.base.SQLResult;
import com.ctrip.platform.dal.dao.client.DalOperationsFactory;
import com.google.common.collect.Lists;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MybatisPlusServiceTest {

    @Resource
    private DbaDalClusterServiceTableMapper mapper;

    @Test
    public void insertTest() {
        Dalservicetable dalservicetable = new Dalservicetable()
                .setId(1L)
                .setName("name")
                .setAge(34);

        mapper.insert(dalservicetable);
    }

    @Test
    public void updateByIdTest() {
        Dalservicetable dalservicetable = new Dalservicetable()
                .setId(1L)
                .setName("name")
                .setAge(34);

        mapper.updateById(dalservicetable);
    }

    @Test
    public void updateInQuery() throws SQLException {
        DalDatabaseOperations operations = DalOperationsFactory.getDalDatabaseOperations("fxcachedb_dalcluster");
        BigDecimal decimal = operations.queryForObject("select 12455131213.142", new DalHints(), SQLResult.mapper((resultSet, column) -> resultSet.getBigDecimal(1)));

        System.out.println(decimal.longValue());
    }

    @Test
    public void updateByLambdaWrapperTest() {
        Dalservicetable dalservicetable = new Dalservicetable()
                .setId(1L)
                .setName("made")
                .setAge(26);

        mapper.update(dalservicetable,
                new LambdaUpdateWrapper<>(dalservicetable)
                        .eq(Dalservicetable::getId, 1L).eq(Dalservicetable::getName, "name")
        );
    }

    @Test
    public void updateByWrapperTest() {
        Dalservicetable dalservicetable = new Dalservicetable()
                .setId(1L)
                .setName("made")
                .setAge(26);

        mapper.update(dalservicetable,
                new LambdaUpdateWrapper<>(Dalservicetable.class)
                        .eq(Dalservicetable::getId, 1L).eq(Dalservicetable::getName, "name")
        );
    }

    @Test
    public void lambdaQueryTest() {
        Dalservicetable dalservicetable = new Dalservicetable()
                .setName("made")
                .setAge(26);

        System.out.println(mapper.selectList(
                new LambdaQueryWrapper<>(Dalservicetable.class).select(Dalservicetable::getId, Dalservicetable::getName, Dalservicetable::getAge).
                        lt(Dalservicetable::getId, 120)
        ));

    }

    @Test
    public void queryTest() {
        Dalservicetable dalservicetable = new Dalservicetable();

        System.out.println(mapper.selectList(
                new QueryWrapper<>(dalservicetable).select("distinct(name)", "id", "age").
                        lt("id", 120)
        ));

        System.out.println(mapper.selectList(
                new QueryWrapper<Dalservicetable>().select(Dalservicetable.class, (t) -> true).
                        lt("id", 120)
        ));

    }

    @Test
    public void test() {
//        ShardingTableRuleConfiguration config = new ShardingTableRuleConfiguration()
    }

}