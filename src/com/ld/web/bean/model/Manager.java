package com.ld.web.bean.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.struts2.json.annotations.JSON;

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

    private Long id;

    private String username;

    private String password;

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

    @Column(nullable = false)
    @JSON(serialize = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Manager() {
    }

    public Manager(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
