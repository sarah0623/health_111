package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Role;
import com.itheima.health.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {
    @Reference
    private RoleService roleService;

    /**
     * 分页查询条件
     *
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        // 调用业务来分页，获取分页结果
        PageResult<Role> pageResult = roleService.findPage(queryPageBean);
        //return pageResult;
        // 返回给页面, 包装到Result, 统一风格
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS, pageResult);
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Role role, Integer[] menuIds) {
        // 调用业务服务
        roleService.add(role, menuIds);
        return new Result(true, MessageConstant.ADD_ROLE_SUCCESS);
    }

    @GetMapping("/findMenuIdsByRoleId")
    public Result findMenuIdsByRoleId(int roleId) {
        // 调用业务查询
        List<Integer> list = roleService.findMenuIdsByRoleId(roleId);
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS, list);
    }

    /**
     * 编辑角色
     * @param role
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Role role,Integer[] menuIds){
        // 调用业务服务
        roleService.update(role,menuIds);
        // 响应结果
        return new Result(true, MessageConstant.EDIT_ROLE_SUCCESS);
    }

    /**
     * 通过id查询
     * @param roleId
     * @return
     */
    @GetMapping("/findById")
    public Result findById(int roleId) {
        // 调用业务服务
        Role role = roleService.findById(roleId);
        // 响应结果
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS, role);
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    public Result deleteById(int id){
        roleService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_ROLE_SUCCESS);
    }

    /**
     * 查询所有
     * @return
     */
    @GetMapping("/findAll")
    public Result findAll() {
        List<Role> all = roleService.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, all);
    }
}
