package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserManageDao {

    //    新增用户
    void add(@RequestBody User userbean);

    //        新增用户和角色的关系
    void addUserRole(@Param("userbeanId") Integer userbeanId, @Param("roleId") Integer roleId);

    //    分页查询
    Page<User> findPage(String queryString);

    //    通过id查询,回显数据
    User findById(int id);

    //    通过id查询选中的角色ids
    List<Integer> findRoleIdByUserId(int id);

    //    编辑
    void update(User userbean);

    //     查询用户是否被角色使用
    int findRoleCountByUserId(int id);

    //    解除用户与角色的关系
    void deleteUserRole(int id);

    //    再删除用户
    void deleteById(int id);


}
