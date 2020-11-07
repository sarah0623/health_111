package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService01;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 体检检查项管理
 */
@RestController
@RequestMapping("menu")
public class MenuController01 {

    @Reference
    private MenuService01 menuService01;

    /**
     * 查询检查项
     *
     * @return
     */
    @RequestMapping("findAll")
    public Result findAll() {
        // 调用服务查询
        List<Menu> menuList = menuService01.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, menuList);
    }

    /**
     * 新增检查项
     *
     * @param menu 接收前端提交过来的formData
     * @return
     */
    @RequestMapping("add")
    public Result add(@RequestBody Menu menu) {
        // 调用服务添加
        boolean flag = menuService01.add(menu);
        return new Result(flag, "新增菜单项成功");
    }

    /**
     * 分页条件查询
     *
     * @param queryPageBean
     * @return
     */
    @RequestMapping("findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        // 调用业务来分页，获取分页结果
        //PageResult<CheckItem> pageResult = checkItemService.findPage(queryPageBean);
        PageResult<Menu> pageResult= menuService01.findPage (queryPageBean);
        //return pageResult;
        // 返回给页面, 包装到Result, 统一风格
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, pageResult);
    }

    /**
     * 删除检查项
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public Result deleteById(int id) {
        // 调用业务服务
        menuService01.deleteById(id);
        // 响应结果
        return new Result(true, "删除菜单项成功!!!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public Result findById(int id) {
        // 调用业务服务
        Menu menu = menuService01.findById(id);
        // 响应结果
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, menu);
    }

    /**
     * 编辑检查项
     *
     * @param menu
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody Menu menu) {
        // 调用业务服务
        menuService01.update(menu);
        // 响应结果
        return new Result(true, "更新菜单成功！！！");
    }

}
