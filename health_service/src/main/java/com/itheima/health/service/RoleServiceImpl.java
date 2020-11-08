package com.itheima.health.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.dao.RoleDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = RoleService.class)
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<Role> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        // 模糊查询 拼接 %
        // 判断是否有查询条件
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())) {
            // 有查询条件，拼接%
            queryPageBean.setQueryString("%" + queryPageBean.getQueryString() + "%");
        }
        // 紧接着的查询语句会被分页
        Page<Role> page = roleDao.findPage(queryPageBean.getQueryString());
        // 封装到分页结果对象中
        return new PageResult<Role>(page.getTotal(), page.getResult());
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @Override
    @Transactional
    public void add(Role role, Integer[] menuIds) {
        // 添加菜单
        roleDao.add(role);
        // 获取角色的id
        Integer roleId = role.getId();
        // 遍历检查项id, 添加角色与菜单的关系
        if (roleId != null) {
            // 有钩选
            for (Integer menuId : menuIds) {
                //添加角色与菜单的关系
                roleDao.addRoleMenu(roleId, menuId);
            }
        }
    }

    /**
     * 编辑角色
     * @param role
     */
    @Override
    @Transactional
    public void update(Role role,Integer[] menuIds) {
        // 先更新角色
        roleDao.update(role);
        // 删除旧关系
        roleDao.deleteRoleMenu(role.getId());
        // 建立新关系
        if (menuIds != null) {
            for (Integer menuId : menuIds) {
                roleDao.addRoleMenu(role.getId(), menuId);
            }
        }
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Override
    public Role findById(int id) {
        return roleDao.findById(id);
    }

    /**
     * 通过id删除
     * @param id
     */
    @Override
    public void deleteById(int id) throws HealthException{
        //先判断这个角色是否被用户使用了
        //调用dao查询检查项的id是否在t_user_role表中存在记录
        int cnt = roleDao.findCountByRoleId(id);

        //被使用了则不能删除
        if (cnt > 0) {
            // 已经被用户使用了，则不能删除，报自定义异常错误
            throw new HealthException(MessageConstant.ROLE_IN_USE);
        }
        // 先删除角色与菜单的关系
        roleDao.deleteRoleMenu(id);
        //没使用就可以调用dao删除
        roleDao.deleteById(id);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /**
     * 通过角色id查询菜单id
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> findMenuIdsByRoleId(int roleId) {
        List<Integer> list = roleDao.findMenuIdsByRoleId(roleId);
        return list;
    }
}

