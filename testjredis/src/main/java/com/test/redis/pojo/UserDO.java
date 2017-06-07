package com.test.redis.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xxx on 2017/6/7.
 */
public class UserDO implements Serializable{

    public static final String OBJECT_KEY = "USER";

    private String name;
    private String password;
    private Integer level;
    private Date createDate;
    private String telephone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getObjectKey() {
        return OBJECT_KEY;
    }

    @Override
    public String toString() {
        return "UserDO{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                ", createDate=" + createDate +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public String getKey() {
        return name;
    }
}
