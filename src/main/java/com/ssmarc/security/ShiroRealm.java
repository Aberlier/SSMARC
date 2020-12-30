package com.ssmarc.security;

import com.ssmarc.dao.DomainAdminMapper;
import com.ssmarc.dao.DomainEmployerMapper;
import com.ssmarc.dao.DomainUserMapper;
import com.ssmarc.entity.DomainAdmin;
import com.ssmarc.entity.DomainEmployer;
import com.ssmarc.entity.DomainUser;
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
    @Autowired
    private DomainEmployerMapper domainEmployerMapper;
    @Autowired
    private DomainUserMapper domainUserMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //此方法是用来判断不同角色展示不同的菜单信息
        /* System.out.println("进入授权方法，进行授权");
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            DomainAdmin admin = (DomainAdmin) principals.getPrimaryPrincipal();
            //获取用户的角色及角色的模块
            Set<Role> roles = admin.getAdminRole();//通过用户查到用户角色
            for (Role role : roles) {//遍历数据
                Set<Module> modules = role.getModules();//通过用户查到用户模块
                for (Module module : modules) {
                    info.addStringPermission(module.getCpermission());
                    //通过用户模块查到模块名并添加到info中。也就是说把查到的模块显示到页面中，没有的就代表没授权
                }
                return list;
            }*/
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
        DomainUser basicUser = domainUserMapper.selectByUserName(username);
        DomainEmployer basicEmpt = domainEmployerMapper.selectByEmployerName(username);
        SimpleAuthenticationInfo simpleAuthenticationInfo = null;
        if (!"".equals(basicAdmin) || basicAdmin != null) {
            //管理员
            if ("1".equals(basicAdmin.getAdminRole())) {
                simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                        basicAdmin.getAdminName(),
                        basicAdmin.getAdminPwd(),
                        basicAdmin.getAdminRole());
            }
        }else if(!"".equals(basicUser) || basicUser != null){
            //用户
            if ("2".equals(basicUser.getUserRole())) {
                simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                        basicUser.getUserName(),
                        basicUser.getUserPwd(),
                        basicUser.getUserRole());
            }
        }else if(!"".equals(basicEmpt) || basicEmpt != null){
            if ("3".equals(basicEmpt.getEmployerRole())) {
                simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                        basicEmpt.getEmployerPwd(),
                        basicEmpt.getEmployerRole(),
                        basicEmpt.getEmployerName());
            }
        }
        if (basicAdmin == null && basicUser==null && basicEmpt == null) {
            throw new AccountException("账号或密码错误");
        }
        if (!password.equals(basicAdmin.getAdminPwd()) && !password.equals(basicEmpt.getEmployerPwd()) && !password.equals(basicUser.getUserPwd())) {
            throw new AccountException("账号或密码错误");
        }
        return simpleAuthenticationInfo;
    }

}
