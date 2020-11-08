package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.User;
import com.sun.xml.internal.ws.handler.HandlerException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserManageService {


    //    新增用户
    void add (User userbean,Integer[] roleIds);

    //    分页查询
    PageResult<User> findPage(@RequestBody QueryPageBean queryPageBean);

    //    通过id查询,回显数据
    User findById(int id);

    //    通过id查询选中的角色ids
    List<Integer> findRoleIdByUserId(int id);

    //    编辑
    void update(User userbean, Integer[] roleIds);

    //    删除
    void deleteById(int id) throws HealthException;


}
