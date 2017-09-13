package com.zmj.demo.handle;

import com.zmj.demo.domain.Result;
import com.zmj.demo.exception.CustomerException;
import com.zmj.demo.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof CustomerException) {
            CustomerException customerException = (CustomerException) e;
            return ResultUtil.error(customerException.getCode(), customerException.getMessage());
        }
        e.printStackTrace();
        logger.info("系统异常");
//        return ResultUtil.error(100, "未知错误");
        return ResultUtil.error(100, e.toString());
    }
}
