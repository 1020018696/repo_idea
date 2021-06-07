package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> roleList = roleService.findAllRole(role);
        ResponseResult result = new ResponseResult(true, 200, "查询角色信息成功", roleList);
        return result;
    }

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        List<Menu> menuList = roleService.findSubMenuListByPid(-1);

        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);

        ResponseResult result = new ResponseResult(true, 200, "获取所有菜单成功", map);

        return result;
    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(int roleId){
        List<String> menuIDList = roleService.findMenuByRoleId(roleId);

        ResponseResult result = new ResponseResult(true, 200, "获取roleid关联菜单成功", menuIDList);

        return result;
    }

    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.RoleContextMenu(roleMenuVo);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);

        return result;
    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(int id){

        roleService.deleteRole(id);

        ResponseResult result = new ResponseResult(true, 200, "删除成功", null);

        return result;
    }
}
