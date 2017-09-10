package com.zmj.demo.service;


import com.zmj.demo.domain.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerService1Test {
    @Autowired
    private CustomerService1 customerService1;

    //  查询所有
    @Test
    public void findAllCustomers() {
        Customer cus = customerService1.findOneWithID(3);
        Assert.assertEquals(cus.getName(), "hello");
    }

    //  增加用户
    @Test
    public void insertCustomer(){
        Customer cus = new Customer();
        cus.setName("小明");
        cus.setPhone("13013141578");
        cus.setEmail("xm@yahoo.com");
        cus.setGender("male");
        cus.setDescription("大家好，我是小明请来的测试人员");
        int i = customerService1.insertCustomer(cus);
        Assert.assertEquals(i, 1);
    }

    //  更新用户
    @Test
    public void updateCustomer(){
        Customer cus = new Customer();
        cus.setName("小明，我是小明家");
        cus.setPhone("13013141578");
        cus.setEmail("xm@yahoo.com");
        cus.setGender("male");
        cus.setDescription("大家好，我是小明请来的测试人员");
        cus.setId(27);
        int i = customerService1.updateCustomer(cus);
        Assert.assertEquals(i, 1);
    }
}
