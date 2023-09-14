package com.ctrip.dal.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.ctrip.platform.dal.dao.annotation.Database;
import com.ctrip.platform.dal.dao.annotation.Type;

import java.sql.Types;
import java.sql.Timestamp;

import com.ctrip.platform.dal.dao.DalPojo;

/**
 * @author lhj刘焕杰
 * @date 2023-08-08
 */
@Entity
@Database(name = "dbadalclustertest01db_dalcluster")
@Table(name = "dalservicetable")
public class Dalservicetable implements DalPojo {

    /**
     * id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(value = Types.BIGINT)
    private Long id;

    /**
     * 姓名:测试用表
     */
    @Column(name = "name")
    @Type(value = Types.VARCHAR)
    private String name;

    /**
     * 年龄:测试用表
     */
    @Column(name = "age")
    @Type(value = Types.INTEGER)
    private Integer age;

    private Timestamp birth;

    /**
     * 更新时间
     */
    @Column(name = "datachange_lasttime", insertable = false, updatable = false)
    @Type(value = Types.TIMESTAMP)
    private Timestamp datachangeLasttime;

    public Long getId() {
        return id;
    }

    public Dalservicetable setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dalservicetable setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Dalservicetable setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Timestamp getBirth() {
        return birth;
    }

    public Dalservicetable setBirth(Timestamp birth) {
        this.birth = birth;
        return this;
    }

    public Timestamp getDatachangeLasttime() {
        return datachangeLasttime;
    }

    public Dalservicetable setDatachangeLasttime(Timestamp datachangeLasttime) {
        this.datachangeLasttime = datachangeLasttime;
        return this;
    }

    @Override
    public String toString() {
        return "Dalservicetable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                ", datachangeLasttime=" + datachangeLasttime +
                '}';
    }
}
