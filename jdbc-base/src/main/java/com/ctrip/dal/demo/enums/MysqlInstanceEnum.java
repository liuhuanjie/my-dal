package com.ctrip.dal.demo.enums;

public enum MysqlInstanceEnum {

    mysql5_7("bbzfxdaltest57db", "10.4.169.242:55111", "t_bbzfst57_5r", "aA1^45KeorpgsF7CmM4t"),

    mysql8("dbadalclustertest01db", "10.21.6.37:55111",   "w_dbadst01_vS", "aA1^HtLB5ppt0Xnq7yM3"),
    dalclustertest01_slave("dbadalclustertest01db", "10.21.6.37:55111",   "t_dbadst01_vu_r", "aA1^mXk0TalBhX8rjdmq"),
    mydb_slave("mydb", "10.21.6.37:55111",   "t_dbadst01_vu_r", "aA1^mXk0TalBhX8rjdmq"),
    ;


    public String dbname;
    public String ipAndPort;
    public String uid;
    public String pwd;

    MysqlInstanceEnum(String dbname, String ipAndPort, String uid, String pwd) {
        this.dbname = dbname;
        this.ipAndPort = ipAndPort;
        this.uid = uid;
        this.pwd = pwd;
    }

}
