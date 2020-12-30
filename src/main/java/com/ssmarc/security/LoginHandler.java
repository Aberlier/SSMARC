package com.ssmarc.security;

import com.ssmarc.entity.DomainUser;
import com.ssmarc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Angular
 * @date ：Created in 2020/12/22 19:05
 * @description：实现登录登出功能类
 * @modified By：
 * @version: v1.0.0$
 */
@RestController
@RequestMapping("ssmarc")
public class LoginHandler {
    @Autowired
    UserService userService;

    @PostMapping("/shirologin")
    public Map<String, Object> shirologin(@RequestParam(value = "userName", required = false) String username,
                                          @RequestParam(value = "userPwd", required = false) String password,
                                          @RequestParam(value = "rememberMe", required = false) String rememberMe) {
        System.out.println(rememberMe);
        Map<String, Object> map = new HashMap<>();
        //  UsernamePasswordToken token = new UsernamePasswordToken(username, EncryptKit.MD5(password));
        //得到Subject,通过SecurityUtils得到Subject，其会自动绑定到当前线程；如果在web环境在请求结束时需要解除绑定
        Subject subject = SecurityUtils.getSubject();
        map.put("token", subject.getSession().getId());
        try {
            if (!subject.isAuthenticated()) {
                //密码进行MD5加密
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, EncryptKit.MD5(password));
                //认证登陆
                subject.login(usernamePasswordToken);
                if (rememberMe != null) {
                    //当checkbox勾选之后，rememberMe就不为null，此时启用rememberMe
                    usernamePasswordToken.setRememberMe(true);
                } else {
                    //关闭remember
                    usernamePasswordToken.setRememberMe(false);
                }
            }
            Session session = subject.getSession();
            //这里设置session过期时间
            session.setTimeout(25000);
            session.setAttribute("currentuser", username);
            map.put("code", 200);
            map.put("msg", "登录成功");
        } catch (UnknownAccountException e) {
            map.put("code", 400);
            map.put("msg", "账号不存在");
            return map;
        } catch (IncompatibleClassChangeError e) {
            map.put("code", 401);
            map.put("msg", "密码错误");
            return map;
        } catch (Exception e) {
            map.put("code", 204);
            map.put("msg", "登陆失败");
            return map;
        }
        return map;
    }

   /* @PostMapping("/shirologin")
    public Map<String, Object> userAndEmplogin(
            @RequestParam(value = "roleName", required = false) String roleName,
            @RequestParam(value = "userName", required = false) String username,
            @RequestParam(value = "userPwd", required = false) String password,
            @RequestParam(value = "rememberMe", required = false) String rememberMe) {
        System.out.println(rememberMe);

        Map<String, Object> map = new HashMap<>();
        //  UsernamePasswordToken token = new UsernamePasswordToken(username, EncryptKit.MD5(password));
        //得到Subject,通过SecurityUtils得到Subject，其会自动绑定到当前线程；如果在web环境在请求结束时需要解除绑定
        Subject subject = SecurityUtils.getSubject();
        map.put("token", subject.getSession().getId());
        try {
            if (!subject.isAuthenticated()) {
                //密码进行MD5加密
                UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, EncryptKit.MD5(password),roleName);
                //认证登陆
                subject.login(usernamePasswordToken);
                if (rememberMe != null) {
                    //当checkbox勾选之后，rememberMe就不为null，此时启用rememberMe
                    usernamePasswordToken.setRememberMe(true);
                } else {
                    //关闭remember
                    usernamePasswordToken.setRememberMe(false);
                }
            }
            Session session = subject.getSession();
            //这里设置session过期时间
            session.setTimeout(25000);
            session.setAttribute("currentuser", username);
            map.put("code", 200);
            map.put("msg", "登录成功");
        } catch (UnknownAccountException e) {
            map.put("code", 400);
            map.put("msg", "账号不存在");
            return map;
        } catch (IncompatibleClassChangeError e) {
            map.put("code", 401);
            map.put("msg", "密码错误");
            return map;
        } catch (Exception e) {
            map.put("code", 204);
            map.put("msg", "登陆失败");
            return map;
        }
        return map;
    }
*/

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping("/shirologout")
    public String shirologout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout();
                //登出成功
                return "redirect:/login.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/ssm/home";
    }

    /**
     * 用户注册方法
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/regist")
    /* public ResultModel registUser(@RequestBody DomainUser user){*/
    public ResultModel registUser(@RequestParam(value = "username", required = false) String username,
                                  @RequestParam(value = "password", required = false) String password) {
        ResultModel resultBean;
        /*if (userPwd.length() == 0) {
            pojo.setUserPwd("123456");
            rec_changeds = true;
        }*/
        DomainUser user = new DomainUser();
        user.setUserName(username);
        user.setUserPwd(password);
        boolean flag = userService.registUser(user);
        if (flag) {
            resultBean = new ResultModel(1, "用户注册成功！");
        } else {
            resultBean = new ResultModel(0, "用户注册失败！");
        }
        return resultBean;
    }


}
