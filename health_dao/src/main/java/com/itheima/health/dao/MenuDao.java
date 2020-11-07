package com.itheima.health.dao;

import com.itheima.health.pojo.Menu;

import java.util.List;

public interface MenuDao {
    List findParentById(Integer id);
    List<Menu> findByParentId(Integer parentId);

}
