package com.ctrip.datasource.demo.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ctrip.dal.demo.entity.Dalservicetable;
import com.ctrip.datasource.demo.mapper.dalservice2db.DalService2DbServiceTableMapper;
import com.ctrip.datasource.demo.mapper.dbadalclustertest01db.DbaDalClusterServiceTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MybatisPlusService {

    @Resource
    private DbaDalClusterServiceTableMapper dbaDalClusterServiceTableMapper;

    @Resource
    private DalService2DbServiceTableMapper dalService2DbServiceTableMapper;

    public void insert(Dalservicetable dalservicetable) {
        dbaDalClusterServiceTableMapper.insert(dalservicetable);
    }

    public void service2insert(Dalservicetable dalservicetable) {
        dalService2DbServiceTableMapper.insert(dalservicetable);
    }

    public void update(Dalservicetable table) {
        dbaDalClusterServiceTableMapper.updateById(table);
    }
}
