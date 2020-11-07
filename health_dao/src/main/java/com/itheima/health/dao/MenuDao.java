package com.itheima.health.dao;

import com.itheima.health.pojo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao {
    List findParentById(Integer id);
    List<Menu> findByParentId(@Param("userId") Integer userId,@Param("parentId")  Integer parentId);

    List<Menu> findMenuListByUserName(String username);

    List<Menu> findChildrenByParentId(Integer id);
}
