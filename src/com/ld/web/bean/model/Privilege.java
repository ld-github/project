package com.ld.web.bean.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 *<p>Title: Privilege</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2015-02-15
 */
@Entity
@Table(name = "t_privilege")
public class Privilege implements Serializable {

    private static final long serialVersionUID = -5841740657120428344L;

    private Long id; // 主键

    private List<Privilege> items; // 子类权限

    private String name; // 名称

    private String description; // 描述

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(targetEntity = Privilege.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId")
    public List<Privilege> getItems() {
        return items;
    }

    public void setItems(List<Privilege> items) {
        this.items = items;
    }

    @Column(length = 32, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Privilege(List<Privilege> items, String name, String description) {
        this.items = items;
        this.name = name;
        this.description = description;
    }

    public Privilege(Long id, List<Privilege> items, String name, String description) {
        this.id = id;
        this.items = items;
        this.name = name;
        this.description = description;
    }

    public Privilege() {
    }

}
