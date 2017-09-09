package com.zmj.demo.service;

import com.zmj.demo.domain.Customer;
import com.zmj.demo.enums.ResultEnum;
import com.zmj.demo.exception.CustomerException;
import com.zmj.demo.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public void insertTwo() {
        Customer cus_a = customer("小明家");
        Customer cus_b = customer("大名家");
        cus_b.setPhone("18550393897");
        customerRepository.save(cus_a);
        customerRepository.save(cus_b);
    }

    private Customer customer(String name){
        Customer cus_a = new Customer();
        cus_a.setName(name);
        cus_a.setEmail("xiaoming@sina.cn");
        cus_a.setGender("female");
        cus_a.setPhone("1312344324");
        cus_a.setDescription("beweijfoewioeio");
        return cus_a;
    }

    public List<Customer> getGender(String gender) throws Exception{
        if (gender.equals("male") || gender.equals("female")) {
            List<Customer> customers = customerRepository.findByGender(gender);
            return customers;
        }
        else if(gender.equals("name")) {
            throw new CustomerException(ResultEnum.PRIMARY_INPUT_NAME);
        }
        else {
            throw new CustomerException(ResultEnum.PRIMARY_INPUT_GENDER);
        }
    }

    /**
     * 通过ID查询一个用户的信息
     * @param id
     * @return Customer对象
     */
    public Customer findOne(Integer id){
        return customerRepository.findOne(id);
    }
}