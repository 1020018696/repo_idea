package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /*
        查询角色列表(条件)
     */
    public List<Role> findAllRole(Role role);

    /*
    根据角色ID查询菜单信息
    */
    List<String> findMenuByRoleId(Integer roleId);

    /*
        删除角色菜单关联表信息
     */
    public void deleteRoleContextMenu(int roleId);

    /*
    角色菜单关联
    */
    void RoleContextMenu(Role_menu_relation role_menu_relation);

    /*
    删除角色
     */
    void deleteRole(Integer roleId);

}
