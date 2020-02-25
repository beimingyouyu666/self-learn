package com.yangk.selflearn.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(String msg) {

        return "hello springboot！" + msg;
    }

    @RequestMapping("/hehe")
    public String hehe(String msg) {

        return "hello springboot！" + msg;
    }

}
