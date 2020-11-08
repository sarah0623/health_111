package com.itheima.health.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.dao.UserManageDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service(interfaceClass = UserManageService.class)
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    private UserManageDao userManageDao;

    //    新增用户
    @Override
    public void add(User userbean,Integer[] roleIds) {
        userManageDao.add(userbean);
        //        获取新增用户的id
        Integer userbeanId = userbean.getId();
        if(null !=userbeanId){
//            遍历拿到角色id
            for (Integer roleId : roleIds) {
                //        新增用户和角色的关系
                userManageDao.addUserRole(userbeanId,roleId);
            }
        }
    }

    //    分页查询
    @Override
    public PageResult<User> findPage(QueryPageBean queryPageBean) {
        //页面与大小
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //如果不为空,就模糊查询
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())) {
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        //条件查询,查询语句会被分页
        Page<User> page = userManageDao.findPage(queryPageBean.getQueryString());
        PageResult<User> pageResult = new PageResult<User>(page.getTotal(), page.getResult());
        return pageResult;

    }

    //    通过id查询,回显数据
    @Override
    public User findById(int id) {
        return userManageDao.findById(id);
    }

    //    通过id查询选中的角色ids
    @Override
    public List<Integer> findRoleIdByUserId(int id) {
        return userManageDao.findRoleIdByUserId(id);
    }

    //    编辑
    @Override
    public void update(User userbean, Integer[] roleIds) {
        userManageDao.update(userbean);
//        解除用户与角色的关系
        userManageDao.deleteUserRole(userbean.getId());
        if(null !=roleIds){
//            遍历拿到角色id
            for (Integer roleId : roleIds) {
                //        再新增用户和角色的关系
                userManageDao.addUserRole(userbean.getId(),roleId);
            }


        }
    }

    //    删除用户
    @Override
    @Transactional
    public void deleteById(int id) throws HealthException{
/*//     查询 判断该用户是否被角色使用
int count = userManageDao.findRoleCountByUserId(id);
//        如果使用了,就抛出异常
if(count>0){
    throw new HealthException("该用户已被角色使用,不能删除哦!");
}*/
//       需要先解除用户与角色的关系
        userManageDao.deleteUserRole(id);
//        再删除用户
        userManageDao.deleteById(id);
    }
}
