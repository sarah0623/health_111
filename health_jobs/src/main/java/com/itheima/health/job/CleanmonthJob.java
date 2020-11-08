package com.itheima.health.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.exception.HealthException;
import com.itheima.health.service.OrderService;
import com.itheima.health.service.OrderSettingService;
import com.itheima.health.utils.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * 定时清理预约设置历史数据
 */
@Component
public class CleanmonthJob {

    /**
     * 订阅服务
     */
    @Reference
    private OrderService orderService;

    @Reference
    private OrderSettingService orderSettingService;


    @Scheduled(cron = "0/10 * * * * ?")//59 59 23 1 * ? | 0/10 * * * * ? | 0 0 2 1 * ?
    @Transactional
    public void cleanmonth() {

        //上个月的 yyyy-mm-dd格式数据
        String bmonth = null;
        String lmonth = null;
        //获取当前的年份
        String nowYear = DateUtils.getNowYear();
        //获取前一年
        int i = Integer.parseInt(nowYear) -1;
        String lastYear = String.valueOf(i);
        //获取上个月月份的int值
        int beforeMonth = DateUtils.getBeforeMonth();
        //如果beforeMonth为0 代表的是12月
        if(beforeMonth == 0){
            beforeMonth =12;
        }
        //判断月份值是否大于10
        if(10 <= beforeMonth && 12 !=beforeMonth){
            //将月份值改为string类型
            String strBeforeMonth = String.valueOf(beforeMonth);
            //大于就直接拼接
            bmonth =  nowYear + "-" + strBeforeMonth + "-" +"01";
            lmonth = nowYear + "-" + strBeforeMonth + "-" +"31";
        }else{

            if(12 == beforeMonth){
                //将月份值改为string类型
                String strBeforeMonth = String.valueOf(beforeMonth);
                //年份用lastYear拼接
                bmonth =  lastYear + "-" + strBeforeMonth + "-" +"01";
                lmonth = lastYear + "-" + strBeforeMonth + "-" +"31";
            }else {

                //将月份值改为string类型
                String strBeforeMonth = String.valueOf(beforeMonth);

                bmonth =  nowYear + "-" + "0"+strBeforeMonth + "-" +"01";
                lmonth = nowYear + "-" +"0"+ strBeforeMonth + "-" +"31";
            }

        }


       //删除order表上月数据
        orderService.deleteByOrder(bmonth, lmonth);
        //删除ordersetting表上月数据
        orderSettingService.deleteByOrderSetting(bmonth, lmonth);

        }
    }

