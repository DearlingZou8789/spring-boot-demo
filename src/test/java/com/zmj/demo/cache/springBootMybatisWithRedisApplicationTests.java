package com.zmj.demo.cache;

import com.zmj.demo.domain.Customer;
import com.zmj.demo.domain.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "dev")
public class springBootMybatisWithRedisApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test(){
        Integer id = 4;
        String url = "http://localhost:" + port + "/girl/v3/customers/" + id;
        Result<HashMap> result = restTemplate.getForObject(url, Result.class);
        Customer customer = new Customer(result.getData());
        Assert.assertEquals(customer.getPhone(), "13164778067");

        customer.setName("我是小明家");
        customer.setDescription("祝我健健康康成长哦！");
        customer.setGender("male");
        String string = customer.toURLEncodeUTF8String();

        //  这里构建请求头
        HttpHeaders headers = new HttpHeaders();
        //  设置请求头中的content-type为application/x-www-form-urlencoded
        MediaType type = MediaType.APPLICATION_FORM_URLENCODED;
        headers.setContentType(type);
        //  组成HttpEntity实体对象
        HttpEntity<String> formEntity = new HttpEntity<String>(string, headers);

        //  发送put请求
        restTemplate.put(url, formEntity, String.class);

        Result<HashMap> result1 = restTemplate.getForObject(url, Result.class);
        Customer newCustomer = new Customer(result1.getData());
        Assert.assertEquals(newCustomer.getName(), customer.getName());
    }
}
