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
import org.hibernate.annotations.Type;

import com.ld.web.enumeration.ExceptionType;

/**
 * 
 *<p>Title: ExceptionLog</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2015-02-15
 */
@Entity
@Table(name = "t_exception_log")
public class ExceptionLog implements Serializable {

    private static final long serialVersionUID = -6693993663180010828L;

    private Long id; // 主键

    private String uid; // 用户id

    private String username; // 用户姓名

    private String className; // 类名

    private String method; // 方法名

    private String level; // 级别

    private String message; // 异常消息

    private ExceptionType exceptionType; // 异常类型

    private Date createDatetime;// 创建时间

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Column(length = 32)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Column
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Column
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Type(type = "text")
    @Column
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Column(columnDefinition = "INT default 0")
    @Enumerated(EnumType.ORDINAL)
    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
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
