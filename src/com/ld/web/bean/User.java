package com.ld.web.bean;

import java.io.Serializable;
import java.security.MessageDigest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * <p>Title: User</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-8
 */
@Entity
@Table(name = "TAB_USER", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User implements Serializable {
    private static final long serialVersionUID = 4284572555216108008L;

    private Long id;

    private String username;

    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(length = 16, nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Sha256
     * 
     * @param input
     * @return
     */
    public static final String sha(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(input.getBytes("UTF-8"));
            byte[] data = md.digest();
            StringBuffer result = new StringBuffer(data.length * 2);
            for (int i = 0; i < data.length; i++) {
                result.append(Integer.toHexString(data[i] & 0xff));
            }
            return result.toString();
        } catch (Exception e) {
            // This should not happen!
            throw new RuntimeException(e);
        }
    }
}
