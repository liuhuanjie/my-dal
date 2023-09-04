package com.ctrip.demo.controller.client;

import com.ctrip.datasource.configure.DalDataSourceFactory;
import com.ctrip.demo.entity.Dalservicetable;
import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.base.DalDatabaseOperations;
import com.ctrip.platform.dal.dao.base.SQLResult;
import com.ctrip.platform.dal.dao.client.DalOperationsFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DalClient3_0_3Test {

    @Test
    public void defaultCollationConnectionTest() throws SQLException {
        try {
            String tableName = "utf8_utf8_general_ci";
            DataSource dataSource = getClusterDataSource("dbadalclustertest01db_dalcluster");
            try (Connection connection = dataSource.getConnection()) {
                System.out.println(connection.getMetaData().getURL());
                showVariables(connection, "show variables like '%collation%';");
                try(Statement statement = connection.createStatement();) {
                    statement.execute("set @temp_name='name';");
                }
                try(Statement statement = connection.createStatement()) {
                    ResultSet resultSet = statement.executeQuery(String.format("select * from %s where name=@temp_name;", tableName));
                    while(resultSet.next()) {
                        System.out.println(resultSet.getString("name"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DataSource getTitanDataSource(String titanKey) throws Exception {
        return new DalDataSourceFactory().createDataSource("dalservice2db_w");
    }

    private DataSource getClusterDataSource(String clusterName) throws Exception {
        return new DalDataSourceFactory().getOrCreateDataSource("dbadalclustertest01db_dalcluster");
    }


    private void showVariables(Connection connection, String showSql) throws SQLException {
        //show  variables like '%collation%';
        String sql=showSql;//生成一条sql语句
        try(Statement stmt=connection.createStatement();) {
            // stmt.executeQuery(sqlset);
            try(ResultSet resultSet = stmt.executeQuery(sql);) {
                while (resultSet.next()) {
                    System.out.print(resultSet.getString("Variable_name") + "         ");
                    System.out.println(resultSet.getString("Value"));
                }
            }
        }
    }
}
