package com.yingjun.dubbox.entity;

import java.io.Serializable;

/**
 * @author yingjun
 */
public class User implements Serializable {
    private Long id;
    private String name;
    private String phone;

    public User() {
    }

    public User(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
