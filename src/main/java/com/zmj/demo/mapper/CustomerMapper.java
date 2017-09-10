package com.zmj.demo.mapper;

import com.zmj.demo.domain.Customer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

//  卧槽，Mapper弄错了。
@org.apache.ibatis.annotations.Mapper
public interface CustomerMapper {

    /**
     * select * from t_customer;
     * @return 所有用户信息
     */
    public List<Customer> selectAll();

    /**
     * 根据Id查找对应的用户信息
     * @param id
     * @return 对应的用户
     */
    public Customer findOneWithID(Integer id);

    /**
     * 插入Customer模型
     * @param c Customer对象
     * @return  是否插入成功
     */
    public int insertCustomer(Customer c);

    /**
     * 更新用户信息
     * @param c Customer对象
     * @return  是否更新成功
     */
    public int updateCustomer(Customer c);

    /**
     * 根据ID删除用户信息
     * @param id    Customer用户ID
     * @return  是否删除成功
     */
    public int deleteCustomerWithID(Integer id);

    /**
     * 根据用户名查找用户信息
     * @param name  用户名
     * @return  查找到的用户信息
     */
    public List<Customer> findCustomersByName(String name);

    /**
     * 根据性别查找用户信息
     * @param gender 用户性别
     * @return  查找到的用户信息
     */
    public List<Customer> findCustomersByGender(String gender);
}
