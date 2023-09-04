package com.ctrip.demo.controller;

import com.ctrip.demo.util.Constants;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class Mysql8BugTestTest {


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception t) {
                throw new RuntimeException(t);
            }
        }
    }

    public static final String MYSQL_URL_FORMAT = "jdbc:mysql://%s/%s?useSSL=false&characterEncoding=UTF-8";


    @Test
    public void mysql8SocketTimeoutTest() throws SQLException {

        try(Connection connection = getConnection()) {
            try(Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("select * from person where id =1;");
                while(resultSet.next()) {
                    Timestamp birth = resultSet.getTimestamp("birth");
                }
                // Ignore for Processing of ResultSet
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(String.format(Constants.MYSQL_URL_FORMAT, "ip:port", "dbname"), "uid", "pwd");
    }

}