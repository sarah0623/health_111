package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.Role;

import java.util.List;

/**
 * 角色管理接口
 */
public interface RoleService {


    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    PageResult<Role> findPage(QueryPageBean queryPageBean);

    /**
     * 新增角色
     * @param role
     * @return
     */
    void add(Role role, Integer[] roleIds);

    /**
     * 编辑角色
     * @param role
     */
    void update(Role role,Integer[] menuIds);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    Role findById(int id);

    /**
     * 通过id删除
     * @param id
     */
    void deleteById(int id)throws HealthException;

    /**
     * 查询所有
     * @return
     */
    List<Role> findAll();

    /**
     * 通过角色id查询菜单id
     * @param roleId
     * @return
     */
    List<Integer> findMenuIdsByRoleId(int roleId);
}
