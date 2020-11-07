package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoleDao {

    /**
     * 分页条件查询
     *
     * @param queryString
     * @return
     */
    Page<Role> findPage(String queryString);

    /**
     * 新增角色
     * @param role
     * @return
     */
    int add(Role role);

    /**
     * 编辑角色
     * @param role
     */
    void update(Role role);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    Role findById(int id);

    /**
     * 通过id删除
     * @param id
     * @return
     */
    int deleteById(int id);

    /**
     * 根据角色id查询用户与角色的关系表个数
     * @param id
     * @return
     */
    int findCountByRoleId(int id);

    /**
     * 查询所有
     * @return
     */
    List<Role> findAll();

    /**
     * 添加角色与菜单关系
     * @param roleId
     * @param menuId
     */
    void addRoleMenu(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);

    /**
     * 删除角色与菜单关系
     * @param roleId
     */
    void deleteRoleMenu(Integer roleId);

    /**
     * 通过角色id查询菜单id
     * @param roleId
     * @return
     */
    List<Integer> findMenuIdsByRoleId(int roleId);
}
