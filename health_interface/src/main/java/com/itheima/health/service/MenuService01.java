package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.Menu;

import java.util.List;

/**
 * 检查项服务接口
 */
public interface MenuService01 {
    /**
     * 查询所有的检查项
     *
     * @return
     */
    List<Menu> findAll();

    /**
     * 新增检查项
     *
     * @param menu
     * @return
     */
    boolean add(Menu menu);

    /**
     * 分页条件查询
     *
     * @param queryPageBean
     * @return
     */
    PageResult<Menu> findPage(QueryPageBean queryPageBean);

    /**
     * 删除检查项
     *
     * @param id
     */
    void deleteById(int id) throws HealthException;

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    Menu findById(int id);

    /**
     * 编辑检查项
     *
     * @param menu
     */
    void update(Menu menu);
}
