package com.lagou.dao;

import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /*
        分页查询所有用户
    */
    public List<User> findAllUserByPage(UserVo userVo);

    /*
        修改用户状态
     */
    public void updateUserStatus(User user);

    /*
    用户登陆
    */
    public User login(User user);

    /*
    根据用户ID清空中间表
    */
    void deleteUserContextRole(Integer userId);
    /*
    分配角色
    */
    void userContextRole(User_Role_relation user_role_relation);

    /*
        1.根据ID查询用户当前角色
    */
    public List<Role> findUserRelationRoleById(int id);

    /**
     * 2. 根据角色id,查询角色拥有的顶级菜单信息
     * */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);
    /**
     * 3. 根据PID 查询子菜单信息
     * */
    public List<Menu> findSubMenuByPid(int pid);
    /**
     * 4. 获取用户拥有的资源权限信息
     * */
    public List<Resource> findResourceByRoleId(List<Integer> ids);



}
