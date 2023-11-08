package com.ctrip.datasource.demo.service;

import com.ctrip.datasource.configure.DalDataSourceFactory;
import com.ctrip.framework.dal.cluster.client.Cluster;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

public class ShardingDatasourceCreateTest {


    public enum ExtendedDatasource {

        Sharding("shardingsphere");

        private String name;

        private ExtendedDatasource(String name) {
            this.name = name;
        }
    }

}
