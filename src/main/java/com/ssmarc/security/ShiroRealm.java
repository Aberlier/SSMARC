package com.ssmarc.security;

import com.ssmarc.dao.DomainAdminMapper;
import com.ssmarc.entity.DomainAdmin;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：Angular
 * @date ：Created in 2020/12/22 18:08
 * @description：安全数据源
 * @modified By：
 * @version: v1.0.0$
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private DomainAdminMapper domainAdminMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 授权认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = String.valueOf(usernamePasswordToken.getPassword());
        DomainAdmin basicAdmin = domainAdminMapper.selectByAdminName(username);
        if (basicAdmin == null) {
            throw new AccountException("账号或密码错误");
        }
        if (!password.equals(basicAdmin.getAdminPwd())) {
            throw new AccountException("账号或密码错误");
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                basicAdmin.getAdminName(), basicAdmin.getAdminPwd(), basicAdmin.getAdminRole());
        return simpleAuthenticationInfo;
    }

}
