package com.lagou.service;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuService {

    /**
     * 查询菜单列表
     * */
    public List<Menu> findAllMenu();

    /*
        根据id查询菜单
     */
    Menu findMenuById(int id);
}
