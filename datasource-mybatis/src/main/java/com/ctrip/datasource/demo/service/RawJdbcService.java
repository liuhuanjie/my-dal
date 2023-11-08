package com.ctrip.datasource.demo.service;

import com.ctrip.datasource.configure.DalDataSourceFactory;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Service
public class RawJdbcService {

    private final String CLUSTER_NAME = "dbadalclustertest01db_dalcluster";
    private final List<String> columns = Lists.newArrayList("id", "name", "age");

    private DataSource dataSource;

    @PostConstruct
    private void init() throws Exception {
        DalDataSourceFactory dalDataSourceFactory = new DalDataSourceFactory();
        dataSource = dalDataSourceFactory.getOrCreateDataSource(CLUSTER_NAME);

    }

    public String queryByStatement(String sql) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try(Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                StringBuilder sb = new StringBuilder();
                while(resultSet.next()) {
                    for (String column : columns) {
                        sb.append(column).append(":").append(resultSet.getString(column)).append("  ");
                    }
                }
                return sb.toString();
            }
        }
    }

    public String queryByPreStatement(String sql, String... params) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(sql)) {
                int i = 1;
                for (String param : params) {
                    statement.setObject(i++, param);
                }
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                StringBuilder sb = new StringBuilder();
                while(resultSet.next()) {
                    for (String column : columns) {
                        sb.append(column).append(":").append(resultSet.getString(column)).append("  ");
                    }
                }
                return sb.toString();
            }
        }
    }
}
