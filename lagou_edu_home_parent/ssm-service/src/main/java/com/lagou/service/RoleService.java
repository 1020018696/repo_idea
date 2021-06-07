package com.lagou.service;

import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

    /*
        查询角色列表(条件)
     */
    public List<Role> findAllRole(Role role);

    /**
     * 查询全部的父子菜单信息
     * */
    public List<Menu> findSubMenuListByPid(int pid);

    /*
    根据角色ID查询菜单信息
    */
    List<String> findMenuByRoleId(Integer roleId);

    /*
        修改角色菜单关联表
     */
    void RoleContextMenu(RoleMenuVo roleMenuVo);

    /*
    删除角色
     */
    void deleteRole(Integer roleId);
}
