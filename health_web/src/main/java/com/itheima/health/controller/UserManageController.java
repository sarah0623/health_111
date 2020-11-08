package com.itheima.health.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.User;
import com.itheima.health.service.UserManageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserManageController {
    @Reference
    private UserManageService userManageService;

//    新增用户
    @PostMapping("/add")
    public Result add(@RequestBody User userbean,Integer[] roleIds){
//        调用业务层,返回结果
        userManageService.add(userbean,roleIds);

        return new Result(true, MessageConstant.ADD_USER_SUCCESS);

    }

//    分页查询
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
//    调用业务层,返回结果
        PageResult<User> pageResult = userManageService.findPage(queryPageBean);
        return new Result(true,MessageConstant.QUERY_USER_SUCCESS,pageResult);

    }

//    通过id查询,回显数据
    @GetMapping("/findById")
    public Result findById(int id){
        //    调用业务层,返回结果
        User userbean = userManageService.findById(id);
        return new Result(true,MessageConstant.QUERY_USER_SUCCESS,userbean);

    }

//    通过id查询选中的角色ids
    @GetMapping("/findRoleIdByUserId")
    public Result findRoleIdByUserId(int id){
        List<Integer> roleIds = userManageService.findRoleIdByUserId(id);
        return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,roleIds);
    }

    //    编辑
@PostMapping("/update")
public Result update(@RequestBody User userbean,Integer[] roleIds){
//        调用业务层,返回结果
    userManageService.update(userbean,roleIds);
    return new Result(true, MessageConstant.EDIT_USER_SUCCESS);

}

    //        删除
@GetMapping("/deleteById")
    public Result deleteById(int id) {
    //        调用业务层,返回结果
    userManageService.deleteById(id);
    return new Result(true,MessageConstant.DELETE_USER_SUCCESS);
}
}
