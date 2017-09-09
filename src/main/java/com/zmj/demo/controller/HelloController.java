package com.zmj.demo.controller;

import com.zmj.demo.properties.GirlProperties;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private GirlProperties girlProperties;

//    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
    @ApiOperation(value = "Hello World", notes = "我是来自火星的Hello World")
    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public String say() {
//        return  cupSize + " Hello Spring Boot!" + "\n age = " + age + "\n content = " + content;
        return girlProperties.getCupSize() + "Hello Spring Boot!" + " age = " + girlProperties.getAge();
//        return "index";
    }

    //  PathVariable url与方法中参数保持一致
    //  RequestParams id与myid可以不一致，其次sayHello中后面直接接?id=100参数设置
//    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
//    public String sayHello(@PathVariable("id") Integer id){
    @ApiOperation(value = "跟某人说Hello", notes = "根据ID，跟某人说Hello")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
    @GetMapping(value = "/sayHello")
    public String sayHello(@RequestParam(value = "id", required = false, defaultValue = "888") Integer myid){
        return "Say Hello" + myid;
    }

}
