package com.ctrip.dal.demo.controller;

import com.ctrip.dal.demo.enums.MysqlInstanceEnum;
import com.ctrip.dal.demo.service.DalService;
import com.ctrip.dal.demo.util.Constants;
import com.ctrip.dal.demo.util.RandomGenerator;
import com.ctrip.datasource.configure.DalDataSourceFactory;
import com.ctrip.dal.demo.entity.CollationInstance;
import com.ctrip.dal.demo.entity.Dalservicetable;
import com.ctrip.framework.dal.cluster.client.cluster.ArchiveStrategyEnum;
import com.ctrip.platform.dal.dao.DalHints;
import com.ctrip.platform.dal.dao.DalTableDao;
import com.ctrip.platform.dal.dao.base.DalDatabaseOperations;
import com.ctrip.platform.dal.dao.base.DalTableOperations;
import com.ctrip.platform.dal.dao.base.SQLResult;
import com.ctrip.platform.dal.dao.client.DalOperationsFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;

@RestController()
public class HelloController {

    private static final String clusterName = "dbatestdbapplydb_dalcluster";
    private static final long TIME_STAMP = 1692575239981L;

    private DalDataSourceFactory factory = new DalDataSourceFactory();
    private DalTableOperations<Dalservicetable> tableOperations ;
    private DalDatabaseOperations dalDatabaseOperations;

    @Resource
    private DalService dalService;

    @PostConstruct
    private void init() throws SQLException {
//        tableOperations = new DalTableDao<>(Dalservicetable.class, clusterName);
//        dalDatabaseOperations = DalOperationsFactory.getDalDatabaseOperations(clusterName);
    }

    @GetMapping(value = "/get/datasource")
    public String getDatasource(@RequestParam(name = "type")String type) throws Exception {
        switch (type) {
            case "dal":
                return getConnectionUrlByDal("dbadalclustertest01db_dalcluster");
            case "driver":
                return getConnectionUrlByDriver(MysqlInstanceEnum.dalclustertest01_slave);
        }

        return "";
    }

    @GetMapping(value = "/mysql8/query")
    public String requestQuery(@RequestParam(value = "type")String type) throws Exception {
        return innerQuery(type);
    }

    @GetMapping(value = "transaction")
    public void transaction(@RequestParam(name = "type")String type) throws SQLException {
       try {
           if ("1".equalsIgnoreCase(type))
                dalService.transactionMethod(null);
           else if ("2".equalsIgnoreCase(type))
               dalService.transactionMethod(new DalHints().archiveStrategy(ArchiveStrategyEnum.ONLINE_ONLY));
           else if("3".equalsIgnoreCase(type))
               dalService.transactionMethod(new DalHints().archiveStrategy(ArchiveStrategyEnum.ARCHIVE_ONLY));
       } catch (Exception e) {
           e.printStackTrace();
       }
    }


    private String innerQuery(String type) throws Exception {
        switch (type) {
            case "dal":
                DalDatabaseOperations dalDatabaseOperations = DalOperationsFactory.getDalDatabaseOperations("dbadalclustertest01db_dalcluster");
                Dalservicetable table = dalDatabaseOperations.queryForObject("select * from v_dalservicetable where status = ? limit 1", new DalHints(), SQLResult.type(Dalservicetable.class), "UlXC0");

                CollationInstance instance = dalDatabaseOperations.queryForObject("show  variables like '%collation_connection%';", new DalHints(), SQLResult.type(CollationInstance.class));
                System.out.println(instance.toString());
                return table.toString();
            case "driver":
                Connection connection = getConnectionByDal("dbadalclustertest01db_dalcluster");
                String sql="show  variables like '%collation%';";//生成一条sql语句
                //String sqlset="set names utf8mb4 collate utf8mb4_general_ci;";//生成一条sql语句
                //String sqlset="set names utf8mb4 ;";//生成一条sql语句
                Statement stmt=connection.createStatement();//创建Statement对象
                // stmt.executeQuery(sqlset);
                ResultSet resultSet = stmt.executeQuery(sql);//执行sql语句
                while (resultSet.next()) {
                    System.out.print(resultSet.getString("Variable_name") + "         ");
                    System.out.println(resultSet.getString("Value"));
                }
//                Statement statement2 = connection.createStatement();
//                ResultSet resultSet2 = statement2.executeQuery("select * from dalservicetable where name = 'UlXC0' limit 1");
//                while(resultSet2.next()) {
//                    System.out.println("id:" + resultSet2.getString("id"));
//                    System.out.println("name:" + resultSet2.getString("name"));
//                }

                Statement stmt2=connection.createStatement();//创建Statement对象
                // stmt.executeQuery(sqlset);
                ResultSet resultSet2 = stmt2.executeQuery("show  variables like '%charact%';");//执行sql语句
                while (resultSet2.next()) {
                    System.out.print(resultSet2.getString("Variable_name") + "         ");
                    System.out.println(resultSet2.getString("Value"));
                }
                return "driver";
        }
        return "";
    }

    private void showVariables(Connection connection, String showSql) throws SQLException {
        //show  variables like '%collation%';
        String sql=showSql;//生成一条sql语句
        Statement stmt=connection.createStatement();
        // stmt.executeQuery(sqlset);
        ResultSet resultSet = stmt.executeQuery(sql);
        while (resultSet.next()) {
            System.out.print(resultSet.getString("Variable_name") + "         ");
            System.out.println(resultSet.getString("Value"));
        }
    }

    @GetMapping(value = "/get/primaryKeys")
    public void getPrimaryKeys() throws SQLException, ClassNotFoundException {
//        dbadalclustertest01db_dalcluster
//        bbzfxdaltest57db_dalcluster
        try (Connection connection = getConnectionByDal("dbadalclustertest01db_dalcluster")) {
            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet resultSet = metaData.getPrimaryKeys(null, "", "dalservicetablesdff");) {
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();
                while (resultSet.next()) {
                    int i=0;
                    while(i++ < columnCount)
                    System.out.println(resultSet.getString(i));
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnectionByDal(String clusterName) throws Exception {
        DataSource dataSource = factory.getOrCreateDataSource(clusterName);
        return dataSource.getConnection();
    }

    private String getConnectionUrlByDal(String clusterName) throws Exception {
        try (Connection connection = getConnectionByDal(clusterName)) {

            return connection.getMetaData().getURL();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://10.21.6.38:55111/dbadalclustertest01db";

        return  DriverManager.getConnection(url, "w_dbadst01_vS", "aA1^HtLB5ppt0Xnq7yM3");
    }

    private String getConnectionUrlByDriver(MysqlInstanceEnum mysqlInstanceEnum) throws ClassNotFoundException {
        try {
            try(Connection conn = DriverManager.getConnection(String.format(Constants.MYSQL_URL_FORMAT, mysqlInstanceEnum.ipAndPort, mysqlInstanceEnum.dbname), mysqlInstanceEnum.uid, mysqlInstanceEnum.pwd)) {
                showVariables(conn, "show  variables like '%collation%';");
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from v_dalservicetable where status = 'tun';");
                return conn.getMetaData().getURL();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping(value = "/ob/select")
    public void selectOb() throws SQLException {
        tableOperations.query("select * from dalservicetable limit 10;", new DalHints());
    }

    @GetMapping(value = "/insert/ob")
    public int insertObPojo(@RequestParam(name = "type")String type) throws SQLException {
        try {
            if (type.equalsIgnoreCase("dal") )
                tableOperations.insert(new DalHints(), RandomGenerator.getStaticTableInstance());
            else if (type.equalsIgnoreCase("flow"))
                insertFlowDataTable();
            else if (type.equalsIgnoreCase("dalflow")) {
                tableOperations.insert(new DalHints(), RandomGenerator.getStaticTableInstance());
                insertFlowDataTable();
            } else if (type.equalsIgnoreCase("flowdal")) {
                insertFlowDataTable();
                dalDatabaseOperations.update("insert into dalservicetable (id, name, age) values (10000000000, 'name', age)", new DalHints() );
//                tableOperations.insert(new DalHints(), RandomGenerator.getStaticTableInstance());
            }

            return 0;
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void insertFlowDataTable() throws SQLException {
        dalDatabaseOperations.update("insert into flow_data_items (data_key, dist_val, time_stamp, expire_time)  values(?, ?, ?, ?) ", new DalHints(), "key", "val", TIME_STAMP, new Date(TIME_STAMP));
    }

}