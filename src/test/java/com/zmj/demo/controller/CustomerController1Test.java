package com.zmj.demo.controller;

import com.zmj.demo.domain.Customer;
import com.zmj.demo.domain.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerController1Test {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void customerList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v3/customers"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("小明");
        customer.setGender("male");
        customer.setPhone("131");
        customer.setEmail("110");
        customer.setDescription("错误");

        String string = customer.toURLEncodeUTF8String();
        System.out.print(string);

        RequestBuilder request = (MockMvcRequestBuilders.put("/v3/customers/3"))
                //  需要设置Put的content-type为[{"key":"Content-Type","value":"application/x-www-form-urlencoded","description":""}]
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(string)
                .accept(MediaType.APPLICATION_JSON_UTF8);
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
