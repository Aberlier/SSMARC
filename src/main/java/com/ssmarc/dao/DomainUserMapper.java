package com.ssmarc.dao;

import com.ssmarc.entity.DomainUser;

public interface DomainUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(DomainUser record);

    int insertSelective(DomainUser record);

    DomainUser selectByUserName(String userName);

    DomainUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(DomainUser record);

    int updateByPrimaryKey(DomainUser record);
}