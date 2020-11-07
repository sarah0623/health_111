package com.itheima.health.service;

import com.itheima.health.pojo.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {
    /**
     * 通过手机号查询会员是否存在
     *
     * @param telephone
     * @return
     */
    Member findByTelephone(String telephone);

    /**
     * 添加会员
     *
     * @param member
     */
    void add(Member member);

    /**
     * 统计过去一年的会员总数
     *
     * @param months
     * @return
     */
    List<Integer> getMemberReport(List<String> months);

    /**
     *性别分类占比
     * @return
     */
    List<Map<String, Object>> getSetmealReportBySex();

    /**
     * 年龄段分类占比
     * @return
     */
    List<Map<String, Object>> getSetmealReportByAge();

    /**
     * 按日历查询会员数量
     * @param months
     * @return
     */
    List<Integer> getMemberReport1(List<String> months);
}
