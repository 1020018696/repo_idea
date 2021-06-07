package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.dao.RoleMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> roles = roleMapper.findAllRole(role);
        return roles;
    }

    @Override
    public List<Menu> findSubMenuListByPid(int pid) {

        List<Menu> menuList = menuMapper.findSubMenuListByPid(pid);
        return menuList;
    }

    @Override
    public List<String> findMenuByRoleId(Integer roleId) {

        List<String> menuIdList = roleMapper.findMenuByRoleId(roleId);
        return menuIdList;
    }

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {

        //1.先清空对应roleId的角色菜单关联表
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //2.添加对应roleId的角色菜单关联表
        for (Integer menuId : roleMenuVo.getMenuIdList()) {

            Role_menu_relation role_menu_relation = new Role_menu_relation();

            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setMenuId(menuId);
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.RoleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(Integer roleId) {

        //先清空中间表
        roleMapper.deleteRoleContextMenu(roleId);

        //删除角色表
        roleMapper.deleteRole(roleId);
    }
}
