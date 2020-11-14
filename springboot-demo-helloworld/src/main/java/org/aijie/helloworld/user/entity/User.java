package org.aijie.helloworld.user.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-10-25 00:49:00
 */
public class User implements Serializable {
    private static final long serialVersionUID = -32476735651328457L;
    
    private Long id;
    
    private String username;
    
    private String name;
    
    private Integer age;
    
    private Double balance;
    
    private Date ndate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getNdate() {
        return ndate;
    }

    public void setNdate(Date ndate) {
        this.ndate = ndate;
    }

}