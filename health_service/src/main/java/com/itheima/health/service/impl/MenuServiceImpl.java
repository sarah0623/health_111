/*
  Auth: lrt
  Date: 2020/11/7
  Time: 9:28
*/
package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.MenuDao;
import com.itheima.health.dao.UserDao;
import com.itheima.health.pojo.Menu;
import com.itheima.health.pojo.User;
import com.itheima.health.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service(interfaceClass = MenuService.class)
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;
    @Autowired
    private UserDao userDao;


    @Override
    public List getMenuByUsername(String username) {
        //根据用户名获取用户对象

        User user =userDao.findByUsername(username);

        List<Menu> parentList=menuDao.findParentById(user.getId());

        parentList.forEach(p->p.setChildren(menuDao.findByParentId(user.getId(),p.getId())));





        return parentList;
    }

    @Override
    public List<Menu> getMenuListByUserName(String username) {
       return menuDao.findMenuListByUserName(username);
    }


}
