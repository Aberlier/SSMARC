package com.ssmarc.dao;

import com.ssmarc.entity.DomainAdmin;

public interface DomainAdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(DomainAdmin record);

    int insertSelective(DomainAdmin record);

    DomainAdmin selectByPrimaryKey(Integer adminId);

    DomainAdmin selectByAdminName(String adminName);

    int updateByPrimaryKeySelective(DomainAdmin record);

    int updateByPrimaryKey(DomainAdmin record);
}