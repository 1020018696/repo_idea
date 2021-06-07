package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> menuList = menuService.findAllMenu();

        ResponseResult result = new ResponseResult(true, 200, "查询所有菜单成功", menuList);

        return result;
    }

    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(int id){

        if(id == -1){
            List<Menu> menuList = roleService.findSubMenuListByPid(id);

            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",menuList);

            ResponseResult result = new ResponseResult(true, 200, "添加回显成功", map);
            return result;
        }else{
            List<Menu> menuList = roleService.findSubMenuListByPid(id);
            Menu menu = menuService.findMenuById(id);

            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",menuList);

            ResponseResult result = new ResponseResult(true, 200, "添加回显成功", map);
            return result;
        }
    }
}
