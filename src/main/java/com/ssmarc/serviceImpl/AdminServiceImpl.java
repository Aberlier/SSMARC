package com.ssmarc.serviceImpl;

import com.ssmarc.dao.DomainAdminMapper;
import com.ssmarc.entity.DomainAdmin;
import com.ssmarc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Angular
 * @date ：Created in 2020/12/30 18:34
 * @description：管理员个人信息接口实现类
 * @modified By：
 * @version: v1.0.0$
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    DomainAdminMapper domainAdminMapper;
    @Override
    public boolean registAdmin(DomainAdmin domainAdmin) {
        boolean registUserBoolean = false;
        int registUserFlag = domainAdminMapper.insert(domainAdmin);
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
