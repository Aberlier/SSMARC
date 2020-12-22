package com.ssmarc.security;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @author ：Angular
 * @date ：Created in 2020/12/22 18:22
 * @description：账户异常错误类
 * @modified By：
 * @version: v1.0.0$
 */
public class AccountException extends AuthenticationException {

    private static final long serialVersionUID = 6423461337343398987L;

    public AccountException(String msg) {
        super(msg);
    }
}
