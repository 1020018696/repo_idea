package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVo;

import java.util.List;

public interface UserService {

    /*
        分页查询所有用户
    */
    public PageInfo findAllUserByPage(UserVo userVo);

    /*
        修改用户状态
     */
    public void updateUserStatus(User user);

    /*
    用户登陆
    */
    public User login(User user) throws Exception;

    /**
     * 根据ID查询用户当前角色
     * */
    public List<Role> findUserRelationRoleById(int id);

    /*
    用户关联角色
    */
    void userContextRole(UserVo userVo);

    /*
     * 获取用户权限
     * */
    ResponseResult getUserPermissions(Integer id);

}
