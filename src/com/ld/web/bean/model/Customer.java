package com.ld.web.bean.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;

import com.ld.web.enumeration.Gender;

/**
 * 
 *<p>Title: Customer</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: 客户信息</p>
 *
 *@author LD
 *
 *@date 2015-09-22
 */
@Entity
@Table(name = "t_customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = -5315517141478453167L;

    private Long id; // 主键

    private String username; // 用户名

    private String telNo; // 电话号码

    private Gender gender; // 性别

    private Date createDatetime; // 创建时间

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(length = 11, nullable = false, unique = true)
    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    @Column(nullable = false, columnDefinition = "INT default 2")
    @Enumerated(EnumType.ORDINAL)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

}
