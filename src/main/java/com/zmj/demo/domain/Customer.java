package com.zmj.demo.domain;

import com.zmj.demo.utils.EncodeUtil;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashMap;

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

    public Customer(){}

    public Customer(HashMap<String, Object> map) {
        setGender((String)map.get("gender"));
        setName((String)map.get("name"));
        setId((Integer)map.get("id"));
        setPhone((String)map.get("phone"));
        setEmail((String)map.get("email"));
        setDescription((String)map.get("description"));
    }

    public HashMap<String, Object> toHashMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("name", getName());
        hashMap.put("gender", getGender());
        hashMap.put("id", getId());
        hashMap.put("phone", getPhone());
        hashMap.put("email", getEmail());
        hashMap.put("description", getDescription());
        return hashMap;
    }

    @Override
    public String toString() {
        return "{id = " + id + "name = " + name + ", gender = " + gender + ", phone = " + phone + ", gender = " + gender + ", description =" + description + "}";
    }

    /**
     * 转换成application/x-www-form-urlencoded对应的字符串
     * @return  application/x-www-form-urlencoded编码的字符串
     */
    public String toURLEncodeUTF8String() {
        //  真是日了狗了了，还得自己携程这种形式
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("name=");
        stringBuilder.append(EncodeUtil.encodeUTF8(getName()));
        stringBuilder.append("&gender=");
        stringBuilder.append(EncodeUtil.encodeUTF8(getGender()));
        stringBuilder.append("&phone=");
        stringBuilder.append(EncodeUtil.encodeUTF8(getPhone()));
        stringBuilder.append("&email=");
        stringBuilder.append(EncodeUtil.encodeUTF8(getEmail()));
        stringBuilder.append("&desc=");
        stringBuilder.append(EncodeUtil.encodeUTF8(getDescription()));
        String string = stringBuilder.toString();
        System.out.print(string);
        return string;
    }
}
