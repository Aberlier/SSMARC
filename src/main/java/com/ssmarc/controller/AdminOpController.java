package com.ssmarc.controller;

import com.ssmarc.entity.DomainAdmin;
import com.ssmarc.security.EncryptKit;
import com.ssmarc.security.ResultModel;
import com.ssmarc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Angular
 * @date ：Created in 2020/12/30 18:39
 * @description：管理员个人信息控制层
 * @modified By：
 * @version: v1.0.0$
 */
@RestController
public class AdminOpController {

    @Autowired
    AdminService adminService;
    /**
     * 管理员注册方法
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/registAdmin")
    /* public ResultModel registUser(@RequestBody DomainUser user){*/
    public ResultModel registAdmin(@RequestParam(value = "username",required = false) String username,
                                   @RequestParam(value = "password",required = false)String password){
        ResultModel resultBean;
        /*if (userPwd.length() == 0) {
            pojo.setUserPwd("123456");
            rec_changeds = true;
        }*/
        DomainAdmin user = new DomainAdmin();
        user.setAdminName(username);
        user.setAdminPwd(EncryptKit.MD5(password));
        boolean flag = adminService.registAdmin(user);
        if (flag){
            resultBean = new ResultModel(1,"用户注册成功！");
        }else {
            resultBean = new ResultModel(0,"用户注册失败！");
        }
        return resultBean;
    }
}
