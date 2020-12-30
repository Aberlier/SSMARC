package com.ssmarc.serviceImpl;

import com.ssmarc.dao.DomainUserMapper;
import com.ssmarc.entity.DomainUser;
import com.ssmarc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Angular
 * @date ：Created in 2020/12/29 19:06
 * @description：用户业务逻辑接口实现类
 * @modified By：
 * @version: v1.0.0$
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    DomainUserMapper domainUserMapper;

    /**
     * 注册用户信息
     * @param user
     * @return
     */
    @Override
    public boolean registUser(DomainUser user) {
        boolean registUserBoolean = false;
        int registUserFlag = domainUserMapper.insert(user);
        switch (registUserFlag) {
            case 1:
                registUserBoolean = true;
                break;
            case 0:
                registUserBoolean = false;
                break;
            default:
                registUserBoolean = false;
        }
        return registUserBoolean;
    }
}
