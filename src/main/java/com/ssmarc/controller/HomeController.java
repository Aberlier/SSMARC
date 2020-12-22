package com.ssmarc.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：Angular
 * @date ：Created in 2020/12/22 19:15
 * @description：主页
 * @modified By：
 * @version: v1.0.0$
 */
public class HomeController {
    @RequestMapping("/home")
    public String home() {
        return "/index";
    }
}
