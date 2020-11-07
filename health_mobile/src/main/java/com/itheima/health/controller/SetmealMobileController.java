package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.SetMeal;
import com.itheima.health.service.SetMealService;
import com.itheima.health.utils.QiNiuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@RestController
@RequestMapping("setmeal")
public class SetmealMobileController {

    @Reference
    private SetMealService setMealService;

    /*Jedis就是使用Java操作Redis的客户端(工具包)
        （JedisPool是jedis的池化技术，使用jedis连接资源时不需要创建，
        而是从连接池中获取一个资源进行redis的操作）
                 1、获取Jedis实例需要从JedisPool中获取
    *            2、用完Jedis实例需要返回给JedisPool
    *            3、如果Jedis在使用过程中出错，则也需要还给JedisPool*/
    @Autowired
    private JedisPool jedisPool;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("getSetmeal")
    public Result getSetmeal() {
        /*从JedisPool获取jedis:jedisPool.getResource()
        *   jedis.get(key):获得字符串类型的数据*/
        Jedis jedis = jedisPool.getResource();
        String setmeals = jedis.get("setmeals");
        /*parseObject(String json, Class type)：
        *                   把json字符串，还原成type类型的java对象*/
        if (null !=setmeals){
            //把json字符串setmeals,还原成List类型的Java对象
            List list = JSON.parseObject(setmeals, List.class);//-------------------------------------------------------------为什么下面的返回结果，不用转化成List类型
            //关闭连接池资源
            jedis.close();
            //返回一个结果集
            return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS, list);
        }else{
            // 查询所有的套餐
            List<SetMeal> setMeals = setMealService.getSetmeal();
            // 套餐里有图片有全路径吗? 拼接全路径
            setMeals.forEach(s -> s.setImg(QiNiuUtils.DOMAIN + s.getImg()));
            //toJSONString(Object obj)：把obj对象里的数据转换成json格式
            String s = JSON.toJSONString(setMeals);
            //把字符串类型的数据s添加到jedis中
            jedisPool.getResource().set("setmeals",s);
            jedis.close();
            return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS, setMeals);
        }

    }

    /**
     * 根据套餐id查询套餐详情信息
     *
     * @param id
     * @return
     */
    @GetMapping("findDetailById")
    public Result findDetailById(Integer id) {
        /*Jedis就是使用Java操作Redis的客户端(工具包)
         *   从JedisPool获取jedis:jedisPool.getResource()
         *   jedis.get(key):获得字符串类型的数据*/
        Jedis jedis = jedisPool.getResource();
        String s1 = jedis.get("setMeal-" + String.valueOf(id));
        SetMeal setMeal1 = JSON.parseObject(s1, SetMeal.class);
        if (null != setMeal1) {
            jedis.close();
            //返回一个结果集
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setMeal1);
        } else {
            // 调用服务查询详情
            SetMeal setMeal = setMealService.findDetailById(id);
            // 设置图片的完整路径
            setMeal.setImg(QiNiuUtils.DOMAIN + setMeal.getImg());
            //把setMeal转换成json格式
            String a = JSON.toJSONString(setMeal);
            //把字符串类型的的数据a添加到jedis中
            jedisPool.getResource().set("setMeal-" + id, a);
            jedis.close();
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setMeal);
        }
    }

    /**
     * 套餐基本信息
     *
     * @param id
     * @return
     */
    @GetMapping("findById")
    public Result findById(int id) {
        // 调用服务查询
        SetMeal s = setMealService.findById(id);
        // 图片的完整路径
        s.setImg(QiNiuUtils.DOMAIN + s.getImg());
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, s);
    }
}
