package com.lagou.controller;


import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){

        PageInfo pageInfo = userService.findAllUserByPage(userVo);

        ResponseResult result = new ResponseResult(true, 200, "分页获取用户信息成功", pageInfo);

        return result;
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(User user){
        userService.updateUserStatus(user);
        ResponseResult result = new ResponseResult(true, 200, "修改用户状态成功", user.getStatus());
        return result;
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User user1 = userService.login(user);

        if(user1 != null){
            //保存access_token
            String access_token = UUID.randomUUID().toString();
            HttpSession session = request.getSession();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            Map<String,Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("user_id",user1.getId());

            map.put("user",user1);

            return new ResponseResult(true,1,"登录成功",map);
        }else{
            return new ResponseResult(true,400,"登录失败，用户名或密码错误",null);
        }
    }

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(int id){
        List<Role> roleList = userService.findUserRelationRoleById(id);

        ResponseResult result = new ResponseResult(true, 200, "获取用户角色成功", roleList);

        return result;
    }

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo){
        userService.userContextRole(userVo);

        ResponseResult result = new ResponseResult(true, 200, "分配角色成功", null);

        return result;
    }

    /**
     * 获取用户权限
     * */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //获取请求头中的 token
        String token = request.getHeader("Authorization");

        //获取session中的access_token
        HttpSession session = request.getSession();
        String access_token = (String)session.getAttribute("access_token");

        //判断
        if(token.equals(access_token)){
            int user_id = (Integer)session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(user_id);
            return result;
        }else{
            ResponseResult result = new ResponseResult(false,400,"获取失败","");
            return result;
        }
    }

}
