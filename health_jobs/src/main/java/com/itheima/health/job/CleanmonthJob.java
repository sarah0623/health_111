package com.itheima.health.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.exception.HealthException;
import com.itheima.health.service.OrderService;
import com.itheima.health.service.OrderSettingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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


    @Scheduled(cron = "0 0 2 1 * ?")//59 59 23 1 * ? | 0/10 * * * * ? |
    public void cleanmonth() {

        List<String> order = orderService.findAllOrder();

        if(0 < order.size()){
            //有数据就删除orderSetting里的所有日期
            orderSettingService.deleteByOrderSetting();
            List<String> orderSetting = orderSettingService.findAllOrderSetting();
            if( 0 == orderSetting.size()){
                //表示orderSetting里面数据已经删除完了,然后开始删除order里的所有数据
                orderService.deleteByOrder();
            }
            }
    }
}
