package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {

    /*
        多条件&分页查询
     */
    public List<Resource> findAllResource(ResourceVo resourceVo);

}
