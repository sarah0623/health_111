/*
  Auth: lrt
  Date: 2020/11/7
  Time: 9:30
*/
package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.Menu;
import com.itheima.health.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Reference
    private MenuService menuService;

    @GetMapping("/getMenuByUsername")
    public Result getMenuByUsername(@RequestParam String username) {

//        List menu = menuService.getMenuByUsername(username);
//
//        return new Result(true, "一给我力giao",menu);

        List<Menu> resultList = menuService.getMenuListByUserName(username);
        return new Result(true, "oligei", resultList);
    }
}
