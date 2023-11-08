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
 * @date 2023-10-25
 */
@Entity
@Database(name = "fxdaltestdb_dalcluster")
@Table(name = "user")
public class User implements DalPojo {

    /**
     * 自增id
     */
    @Id
    @Column(name = "id")
    @Type(value = Types.BIGINT)
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "name")
    @Type(value = Types.VARCHAR)
    private String name;

    /**
     * 年龄
     */
    @Column(name = "age")
    @Type(value = Types.INTEGER)
    private Integer age;

    /**
     * 更新时间
     */
    @Column(name = "datachange_lasttime", insertable = false, updatable = false)
    @Type(value = Types.TIMESTAMP)
    private Timestamp datachangeLasttime;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Timestamp getDatachangeLasttime() {
        return datachangeLasttime;
    }

    public void setDatachangeLasttime(Timestamp datachangeLasttime) {
        this.datachangeLasttime = datachangeLasttime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", datachangeLasttime=" + datachangeLasttime +
                '}';
    }
}
