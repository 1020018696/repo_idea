package com.lagou.controller;

import com.lagou.domain.Test;
import com.lagou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //@Controller + @ResponseBody,此时该类下的所有方法，其返回值都是JSON格式的字符串
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/findAllTest")
    public List<Test> findAllTest(){
        List<Test> list = testService.findAllTest();
        return list;
    }
}
