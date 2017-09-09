package com.zmj.demo.domain;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

//  实体类
@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1435515995276255188L;

    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    @NotNull(message = "性别不能为空")
    //  这样只匹配male或者female
    @Pattern(regexp = "male|female", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String gender;
    private String phone;
    private String email;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "{name = " + name + ", gender = " + gender + ", phone = " + phone + ", gender = " + gender + ", description =" + description + "}";
    }
}
