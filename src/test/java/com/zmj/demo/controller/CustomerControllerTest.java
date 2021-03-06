package com.zmj.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc   //调用API请求数据
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void customerList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/customers")).andExpect(MockMvcResultMatchers.status().isOk());
    }

}