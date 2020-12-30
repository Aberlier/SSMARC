package com.ssmarc.dao;

import com.ssmarc.entity.DomainEmployer;

public interface DomainEmployerMapper {
    int deleteByPrimaryKey(Integer employerId);

    int insert(DomainEmployer record);

    int insertSelective(DomainEmployer record);

    DomainEmployer selectByEmployerName(String employerName);

    DomainEmployer selectByPrimaryKey(Integer employerId);

    int updateByPrimaryKeySelective(DomainEmployer record);

    int updateByPrimaryKey(DomainEmployer record);
}