package com.zmj.demo.respository;

import java.util.List;

import com.zmj.demo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

//  DAO层，继承JPA
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

    //  通过姓名查询
    //  函数需要根据findBy+数据中的字段
    public List<Customer> findByName(String name);

    //  通过性别查询
    public List<Customer> findByGender(String gender);
}
