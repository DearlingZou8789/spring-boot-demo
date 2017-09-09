package com.zmj.demo;

import com.zmj.demo.domain.Customer;
import com.zmj.demo.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest //启动SpringBoot工程
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void findOneTest(){
        Customer cus = customerService.findOne(17);
        Assert.assertEquals(new String("我是小明家的兄弟2"), cus.getName());
    }
}
