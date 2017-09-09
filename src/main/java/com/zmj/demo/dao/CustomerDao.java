package com.zmj.demo.dao;

import com.zmj.demo.domain.Customer;
import com.zmj.demo.mapper.CustomerMapper;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerDao {

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * select * from t_customer;
     * @return 所有用户信息
     */
    public List<Customer> selectAll() {
        return customerMapper.selectAll();
    }

    /**
     * 根据Id查找对应的用户信息
     * @param id
     * @return 对应的用户
     */
    public Customer findOneWithID(Integer id){
        return customerMapper.findOneWithID(id);
    }

    /**
     * 插入Customer模型
     * @param c Customer对象
     * @return  是否插入成功
     */
    public int insertCustomer(Customer c){
        return customerMapper.insertCustomer(c);
    }

    /**
     * 更新用户信息
     * @param c Customer对象
     * @return  是否更新成功
     */
    public int updateCustomer(Customer c){
        return customerMapper.updateCustomer(c);
    }

    /**
     * 根据ID删除用户信息
     * @param id    Customer用户ID
     * @return  是否删除成功
     */
    public int deleteCustomerWithID(Integer id){
        return customerMapper.deleteCustomerWithID(id);
    }

    /**
     * 根据用户名查找用户信息
     * @param name  用户名
     * @return  查找到的用户信息
     */
    public List<Customer> findCustomersByName(String name){
        return customerMapper.findCustomersByName(name);
    }

    /**
     * 根据性别查找用户信息
     * @param gender 用户性别
     * @return  查找到的用户信息
     */
    public List<Customer> findCustomersByGender(String gender){
        return customerMapper.findCustomersByGender(gender);
    }
}
