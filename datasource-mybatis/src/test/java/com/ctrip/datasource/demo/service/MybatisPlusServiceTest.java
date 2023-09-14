package com.ctrip.datasource.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ctrip.dal.demo.entity.Dalservicetable;
import com.ctrip.datasource.demo.mapper.dbadalclustertest01db.DbaDalClusterServiceTableMapper;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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
                new LambdaUpdateWrapper<>(dalservicetable)
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
                new QueryWrapper<>(dalservicetable).select("max(age)", "id", "name").
                        lt("id", 120)
        ));

    }

}