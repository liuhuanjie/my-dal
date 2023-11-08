package com.ctrip.datasource.demo.enums;

import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;

public enum ExtendedDatasource {

    Sharding("shardingsphere");

    private String name;

    private ExtendedDatasource(String name) {
        this.name = name;
    }
}
