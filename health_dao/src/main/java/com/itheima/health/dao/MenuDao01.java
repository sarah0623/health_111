package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.Menu;

import java.util.List;

public interface MenuDao01 {
    /**
     * 查询 所有检查项
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
    int add(Menu menu);

    /**
     * 分页条件查询
     *
     * @param queryString
     * @return
     */
    Page<Menu> findPage(String queryString);

    /**
     * 检查 检查项是否被检查组使用了
     *
     * @param id
     * @return
     */
    int findCountByCheckItemId(int id);

    /**
     * 通过id删除检查项
     *
     * @param id
     */
    void deleteById(int id);

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
