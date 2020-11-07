package com.itheima.health.service;


import com.itheima.health.pojo.Menu;

import java.util.List;

public interface MenuService {
    List getMenuByUsername(String username);

    List<Menu> getMenuListByUserName(String username);
}
