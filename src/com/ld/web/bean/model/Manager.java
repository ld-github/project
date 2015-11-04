package com.ld.web.bean.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * <p>Title: Manager</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
@Entity
@Table(name = "t_manager", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class Manager implements Serializable {

    private static final long serialVersionUID = 4284572555216108008L;

    private Long id; // 主键

    private String username; // 登录名

    private String password; // 密码

    private Date createDatetime; // 创建时间

    private boolean administrator; // 是否是超级管理员

    private boolean available; // 是否可用

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JSON(serialize = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(length = 32, nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JSON(serialize = false)
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JSON(format = "yyyy-MM-dd HH:mm:ss")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    @Column(nullable = false, columnDefinition = "varchar(1) default 'N' ")
    @Type(type = "yes_no")
    public boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    @Column(nullable = false, columnDefinition = "varchar(1) default 'N' ")
    @Type(type = "yes_no")
    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Manager() {
    }

    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
