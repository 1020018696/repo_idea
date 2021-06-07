package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceService {

    /*
       多条件&分页查询
    */
    public PageInfo findAllResource(ResourceVo resourceVo);
}
