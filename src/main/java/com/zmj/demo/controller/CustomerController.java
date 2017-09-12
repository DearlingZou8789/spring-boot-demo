package com.zmj.demo.controller;

import java.util.List;

import com.zmj.demo.domain.Customer;
import com.zmj.demo.domain.Result;
import com.zmj.demo.handle.ExceptionHandle;
import com.zmj.demo.respository.CustomerRepository;
import com.zmj.demo.service.CustomerService;
import com.zmj.demo.utils.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v2")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final static ExceptionHandle exceptionHandle = new ExceptionHandle();

    /*
    *   查询所有用户信息
    *
    */
    @ApiOperation(value = "获取所有用户", notes = "获取所有用户信息")
    @GetMapping(value="/customers")
    public List<Customer> customerList() {
        //  之前使用JPA查询
        return customerRepository.findAll();

    }

    /*
    *   插入一个新的用户
    */
    @ApiOperation(value = "添加用户", notes = "根据传入的Customer模型，添加该用户到数据库中")
    @ApiImplicitParam(name = "cus_a", value = "插入的用户信息", required = true, dataType = "Customer")
    @PostMapping(value = "/customers")
    //  直接将cus_a对象作为参数，插入数据库中
    //  将body类型设置为form-data即可
    public Result<Customer> cutomerAdd(@Valid Customer cus_a, BindingResult bindingResult) {
        logger.info("我被调用了");

        if (bindingResult.hasErrors()){
            return ResultUtil.error(400, bindingResult.getFieldError().getDefaultMessage());
        }
        Customer cus = new Customer();
        cus.setName(cus_a.getName());
        cus.setGender(cus_a.getGender());
        cus.setEmail(cus_a.getEmail());
        cus.setPhone(cus_a.getPhone());
        cus.setDescription(cus_a.getDescription());
        return ResultUtil.success(customerRepository.save(cus));
    }

    //  查询一个用户
    @ApiOperation(value = "查询用户", notes = "根据用户ID查询用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", defaultValue = "1")
    @GetMapping(value = "/customers/{id}")
    public  Result<Customer> findOneCustomer(@PathVariable("id") Integer id) {
        Customer cus = customerRepository.findOne(id);
        if (cus != null){
            return ResultUtil.success(cus);
        }
        return ResultUtil.error(100, "Have no such customer");
    }

    //  更新一个用户
    @ApiOperation(value = "更新用户", notes = "根据ID,name,gender,email,phone,desc更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Interger", defaultValue = "1", paramType = "form"),
            @ApiImplicitParam(name = "name", value = "用户名字", required = false, dataType = "String", defaultValue = "小明", paramType = "form"),
            @ApiImplicitParam(name = "gender", value = "性别", required = false, dataType = "String", defaultValue = "female", paramType = "form"),
            @ApiImplicitParam(name = "phone", value = "电话号码", required = false, dataType = "String", defaultValue = "13164778067", paramType = "form"),
            @ApiImplicitParam(name = "email", value = "电子邮件", required = false, dataType = "String", defaultValue = "zmj27404@sina.cn", paramType = "form"),
            @ApiImplicitParam(name = "desc", value = "描述信息", required = false, dataType = "String", defaultValue = "我就是小明同学", paramType = "form"),
    })
    @PutMapping(value = "/customers/{id}")
    //  得将body类型设置为application/x-www-form-urlencoded
    //  @PathVariable   路径名称
    //  @RequestParam   请求参数
    public Result<Customer> updateCustomer(@PathVariable("id") Integer id,
                                   @RequestParam("name") String name,
                                   @RequestParam("gender") String gender,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("email") String email,
                                   @RequestParam("desc") String desc
                                   ) {
        Customer cus = new Customer();
        cus.setId(id);
        cus.setDescription(desc);
        cus.setName(name);
        cus.setGender(gender);
        cus.setPhone(phone);
        cus.setEmail(email);
        Customer result = customerRepository.save(cus);
        if (result != null){
            return ResultUtil.success(result);
        }
        return ResultUtil.error(100, "update Customer " + id + "failure");
    }

    //  删除一个用户
    @ApiOperation(value = "删除用户", notes = "根据用户ID删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", defaultValue = "2")
    @DeleteMapping(value = "/customers/{id}")
    public  String deleteCustomer(@PathVariable("id") Integer id) {
        try {
            customerRepository.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
        return "OK";
    }

    //  通过名字查询用户
    @ApiOperation(value = "查询用户", notes = "根据用户名字查询用户")
    @ApiImplicitParam(name = "name", value = "用户名字", required = true, dataType = "String", defaultValue = "小明")
    @GetMapping(value = "/customers/name/{name}")
    public Result<List<Customer>> customerByName(@PathVariable("name") String name){
        List<Customer> list = customerRepository.findByName(name);
        if (list != null) {
            return ResultUtil.success(list);
        }
        return ResultUtil.error(100, "No Customers named " + name);
    }

    //  调用服务写入数据
    @ApiOperation(value = "写入两个用户信息", notes = "调用该接口写入两条数据")
        @GetMapping(value = "/customers/service")
    public void serviceStart(){
        customerService.insertTwo();
    }

    //  查询性别(自己处理异常，不通过系统处理)
    @ApiOperation(value = "查询用户", notes = "根据用户性别查询用户")
    @ApiImplicitParam(name = "gender", value = "用户性别", required = true, dataType = "String", defaultValue = "male")
    @GetMapping(value = "/customers/gender/hand/{gender}")
    public Result<List<Customer>> getHandGender(@PathVariable("gender") String gender) {
        try {
            List<Customer> customers = customerService.getGender(gender);
            return ResultUtil.success(customers);
        }
        catch (Exception e){
            return exceptionHandle.handle(e);
        }
    }

    //  查询性别（交给系统处理，但是问题是只能处理异常，正确没法处理)
    @ApiOperation(value = "查询用户", notes = "根据用户性别查询用户")
    @ApiImplicitParam(name = "gender", value = "用户性别", required = true, dataType = "String", defaultValue = "male")
    @GetMapping(value = "/customers/gender/{gender}")
    public void getGender(@PathVariable("gender") String gender) throws Exception{
        customerService.getGender(gender);
    }
}
