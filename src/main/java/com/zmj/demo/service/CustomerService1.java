package com.zmj.demo.service;

import com.zmj.demo.dao.CustomerDao;
import com.zmj.demo.domain.Customer;
import com.zmj.demo.enums.ResultEnum;
import com.zmj.demo.exception.CustomerException;
import com.zmj.demo.mapper.CustomerMapper;
import com.zmj.demo.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
public class CustomerService1{
    @Autowired
    private CustomerDao customerDao;

    @Transactional
    public void insertTwo() {
        Customer cus_a = customer("小明家");
        Customer cus_b = customer("大名家");
        cus_b.setPhone("18550393897");
        customerDao.insertCustomer(cus_a);
        customerDao.insertCustomer(cus_a);
    }

    private Customer customer(String name){
        Customer cus_a = new Customer();
        cus_a.setName(name);
        cus_a.setEmail("xiaoming@sina.cn");
        cus_a.setGender("female");
        cus_a.setPhone("1312344324");
        cus_a.setDescription("abeefewrewqrewqerewqrew");
        return cus_a;
    }

    public List<Customer> getGender(String gender) throws Exception{
        if (gender.equals("male") || gender.equals("female")) {
            List<Customer> customers = customerDao.findCustomersByGender(gender);
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
        return customerDao.findOneWithID(id);
    }

    public List<Customer> selectAll() {
        return customerDao.selectAll();
    }

    public Customer findOneWithID(Integer id) {
        return customerDao.findOneWithID(id);
    }

    public Customer insertCustomer(Customer c) {
        int result = customerDao.insertCustomer(c);
        if (result == 1) {
            return customerDao.findLastInsertCustomer();
        }
        return null;
    }

    public Customer updateCustomer(Customer c) {
        customerDao.updateCustomer(c);
        return customerDao.findOneWithID(c.getId());
    }

    public int deleteCustomerWithID(Integer id) {
        return customerDao.deleteCustomerWithID(id);
    }

    public List<Customer> findCustomersByName(String name) {
        return customerDao.findCustomersByName(name);
    }

    public List<Customer> findCustomersByGender(String gender) {
        return customerDao.findCustomersByGender(gender);
    }
}