package com.ctrip.dal.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.ctrip.platform.dal.dao.annotation.Database;
import com.ctrip.platform.dal.dao.annotation.Sensitive;
import com.ctrip.platform.dal.dao.annotation.Type;
import java.sql.Types;
import java.sql.Timestamp;

import com.ctrip.platform.dal.dao.DalPojo;

/**
 * @author lhj刘焕杰
 * @date 2023-09-04
 */
@Entity
@Database(name = "fxcachedb_dalcluster")
@Table(name = "cache_cluster")
public class CacheCluster implements DalPojo {

    /**
     * ID
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(value = Types.INTEGER)
    private Integer iD;

    /**
     * 集群名称
     */
    @Column(name = "Name")
    @Type(value = Types.VARCHAR)
    private String name;

    /**
     * 子环境名
     */
    @Column(name = "subenv")
    @Type(value = Types.VARCHAR)
    private String subenv;

    /**
     * 集群名称
     */
    @Column(name = "Status")
    @Type(value = Types.INTEGER)
    private Integer status;

    /**
     * 联系人
     */
    @Column(name = "Contactor")
    @Type(value = Types.VARCHAR)
    private String contactor;

    /**
     * 联系人Email
     */
    @Column(name = "Email")
    @Type(value = Types.VARCHAR)
    private String email;

    /**
     * 产线ID
     */
    @Column(name = "ProductLineID")
    @Type(value = Types.INTEGER)
    private Integer productLineID;

    /**
     * 产线名称
     */
    @Column(name = "ProductLineName")
    @Type(value = Types.VARCHAR)
    private String productLineName;

    /**
     * 组织ID
     */
    @Column(name = "OrganizationID")
    @Type(value = Types.INTEGER)
    private Integer organizationID;

    /**
     * 组织部门名称
     */
    @Column(name = "OrganizationName")
    @Type(value = Types.VARCHAR)
    private String organizationName;

    /**
     * DBNumber
     */
    @Column(name = "DBNumber")
    @Type(value = Types.INTEGER)
    private Integer dBNumber;

    /**
     * 备注
     */
    @Column(name = "Remark")
    @Type(value = Types.VARCHAR)
    private String remark;

    /**
     * 时间戳
     */
    @Column(name = "Timestamps")
    @Type(value = Types.TIMESTAMP)
    private Timestamp timestamps;

    /**
     * 是否多IDC
     */
    @Column(name = "UsingIDC")
    @Type(value = Types.INTEGER)
    private Integer usingIDC;

    /**
     * xpipe主机房
     */
    @Column(name = "xpipemasteridc")
    @Type(value = Types.VARCHAR)
    private String xpipemasteridc;

    /**
     * 是否自动切换
     */
    @Column(name = "autoswitch")
    @Type(value = Types.INTEGER)
    private Integer autoswitch;

    /**
     * 集群类型，支持OPS
     */
    @Column(name = "clustertype")
    @Type(value = Types.INTEGER)
    private Integer clustertype;

    /**
     * redis集群位置
     */
    @Column(name = "location")
    @Type(value = Types.INTEGER)
    private Integer location;

    /**
     * Group根ID
     */
    @Column(name = "clustergroupid")
    @Type(value = Types.INTEGER)
    private Integer clustergroupid;

    /**
     * 是否接入CRedis
     */
    @Column(name = "isstandard")
    @Type(value = Types.INTEGER)
    private Integer isstandard;

    /**
     * 0:Katama,1:Hash200
     */
    @Column(name = "hashtype")
    @Type(value = Types.INTEGER)
    private Integer hashtype;

    /**
     * 创建人
     */
    @Column(name = "creator")
    @Type(value = Types.VARCHAR)
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "createtime")
    @Type(value = Types.TIMESTAMP)
    private Timestamp createtime;

    /**
     * 是否删除
     */
    @Column(name = "IsDeleted")
    @Type(value = Types.BIT)
    private Boolean isDeleted;

    /**
     * 是否哨兵监控
     */
    @Column(name = "SentinelStatus")
    @Type(value = Types.INTEGER)
    private Integer sentinelStatus;

    /**
     * sentinel working in cluster
     */
    @Column(name = "sentinelworkstatus")
    @Type(value = Types.INTEGER)
    private Integer sentinelworkstatus;

    /**
     * 是否生产集群
     */
    @Column(name = "IsPro")
    @Type(value = Types.INTEGER)
    private Integer isPro;

    /**
     * 0未知,1核心,2非核心
     */
    @Column(name = "importance")
    @Type(value = Types.INTEGER)
    private Integer importance;

    /**
     * 更新时间
     */
    @Column(name = "datachange_lasttime", insertable = false, updatable = false)
    @Type(value = Types.TIMESTAMP)
    private Timestamp datachangeLasttime;

    /**
     * 修改时间
     */
    @Column(name = "updatetime")
    @Type(value = Types.TIMESTAMP)
    private Timestamp updatetime;

    /**
     * 集群路由规则
     */
    @Column(name = "routestrategy")
    @Type(value = Types.INTEGER)
    private Integer routestrategy;

    /**
     * 不可用机房列表
     */
    @Column(name = "excludedidcs")
    @Type(value = Types.VARCHAR)
    private String excludedidcs;

    /**
     * 普通用户邮箱，支持邮件组
     */
    @Column(name = "users")
    @Type(value = Types.VARCHAR)
    private String users;

    /**
     * 集群管理员邮箱
     */
    @Column(name = "admins")
    @Type(value = Types.VARCHAR)
    private String admins;

    /**
     * 集群压测开关
     */
    @Column(name = "openpressurelane")
    @Type(value = Types.BIT)
    private Boolean openpressurelane;

    /**
     * 集群压测DB Number
     */
    @Column(name = "pressurelanedbnumber")
    @Type(value = Types.INTEGER)
    private Integer pressurelanedbnumber;

    /**
     * 是否正在运维变更；0不是，1是。
     */
    @Column(name = "operating")
    @Type(value = Types.BIT)
    private Boolean operating;

    /**
     * 是否是非对称集群
     */
    @Column(name = "ishetero")
    @Type(value = Types.BIT)
    private Boolean ishetero;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubenv() {
        return subenv;
    }

    public void setSubenv(String subenv) {
        this.subenv = subenv;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContactor() {
        return contactor;
    }

    public void setContactor(String contactor) {
        this.contactor = contactor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getProductLineID() {
        return productLineID;
    }

    public void setProductLineID(Integer productLineID) {
        this.productLineID = productLineID;
    }

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }

    public Integer getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(Integer organizationID) {
        this.organizationID = organizationID;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Integer getDBNumber() {
        return dBNumber;
    }

    public void setDBNumber(Integer dBNumber) {
        this.dBNumber = dBNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Timestamp getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(Timestamp timestamps) {
        this.timestamps = timestamps;
    }

    public Integer getUsingIDC() {
        return usingIDC;
    }

    public void setUsingIDC(Integer usingIDC) {
        this.usingIDC = usingIDC;
    }

    public String getXpipemasteridc() {
        return xpipemasteridc;
    }

    public void setXpipemasteridc(String xpipemasteridc) {
        this.xpipemasteridc = xpipemasteridc;
    }

    public Integer getAutoswitch() {
        return autoswitch;
    }

    public void setAutoswitch(Integer autoswitch) {
        this.autoswitch = autoswitch;
    }

    public Integer getClustertype() {
        return clustertype;
    }

    public void setClustertype(Integer clustertype) {
        this.clustertype = clustertype;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Integer getClustergroupid() {
        return clustergroupid;
    }

    public void setClustergroupid(Integer clustergroupid) {
        this.clustergroupid = clustergroupid;
    }

    public Integer getIsstandard() {
        return isstandard;
    }

    public void setIsstandard(Integer isstandard) {
        this.isstandard = isstandard;
    }

    public Integer getHashtype() {
        return hashtype;
    }

    public void setHashtype(Integer hashtype) {
        this.hashtype = hashtype;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getSentinelStatus() {
        return sentinelStatus;
    }

    public void setSentinelStatus(Integer sentinelStatus) {
        this.sentinelStatus = sentinelStatus;
    }

    public Integer getSentinelworkstatus() {
        return sentinelworkstatus;
    }

    public void setSentinelworkstatus(Integer sentinelworkstatus) {
        this.sentinelworkstatus = sentinelworkstatus;
    }

    public Integer getIsPro() {
        return isPro;
    }

    public void setIsPro(Integer isPro) {
        this.isPro = isPro;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public Timestamp getDatachangeLasttime() {
        return datachangeLasttime;
    }

    public void setDatachangeLasttime(Timestamp datachangeLasttime) {
        this.datachangeLasttime = datachangeLasttime;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getRoutestrategy() {
        return routestrategy;
    }

    public void setRoutestrategy(Integer routestrategy) {
        this.routestrategy = routestrategy;
    }

    public String getExcludedidcs() {
        return excludedidcs;
    }

    public void setExcludedidcs(String excludedidcs) {
        this.excludedidcs = excludedidcs;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getAdmins() {
        return admins;
    }

    public void setAdmins(String admins) {
        this.admins = admins;
    }

    public Boolean getOpenpressurelane() {
        return openpressurelane;
    }

    public void setOpenpressurelane(Boolean openpressurelane) {
        this.openpressurelane = openpressurelane;
    }

    public Integer getPressurelanedbnumber() {
        return pressurelanedbnumber;
    }

    public void setPressurelanedbnumber(Integer pressurelanedbnumber) {
        this.pressurelanedbnumber = pressurelanedbnumber;
    }

    public Boolean getOperating() {
        return operating;
    }

    public void setOperating(Boolean operating) {
        this.operating = operating;
    }

    public Boolean getIshetero() {
        return ishetero;
    }

    public void setIshetero(Boolean ishetero) {
        this.ishetero = ishetero;
    }

    @Override
    public String toString() {
        return "CacheCluster{" +
                "iD=" + iD +
                ", name='" + name + '\'' +
                ", subenv='" + subenv + '\'' +
                ", status=" + status +
                ", contactor='" + contactor + '\'' +
                ", email='" + email + '\'' +
                ", productLineID=" + productLineID +
                ", productLineName='" + productLineName + '\'' +
                ", organizationID=" + organizationID +
                ", organizationName='" + organizationName + '\'' +
                ", dBNumber=" + dBNumber +
                ", remark='" + remark + '\'' +
                ", timestamps=" + timestamps +
                ", usingIDC=" + usingIDC +
                ", xpipemasteridc='" + xpipemasteridc + '\'' +
                ", autoswitch=" + autoswitch +
                ", clustertype=" + clustertype +
                ", location=" + location +
                ", clustergroupid=" + clustergroupid +
                ", isstandard=" + isstandard +
                ", hashtype=" + hashtype +
                ", creator='" + creator + '\'' +
                ", createtime=" + createtime +
                ", isDeleted=" + isDeleted +
                ", sentinelStatus=" + sentinelStatus +
                ", sentinelworkstatus=" + sentinelworkstatus +
                ", isPro=" + isPro +
                ", importance=" + importance +
                ", datachangeLasttime=" + datachangeLasttime +
                ", updatetime=" + updatetime +
                ", routestrategy=" + routestrategy +
                ", excludedidcs='" + excludedidcs + '\'' +
                ", users='" + users + '\'' +
                ", admins='" + admins + '\'' +
                ", openpressurelane=" + openpressurelane +
                ", pressurelanedbnumber=" + pressurelanedbnumber +
                ", operating=" + operating +
                ", ishetero=" + ishetero +
                '}';
    }
}
