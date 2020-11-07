package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.MemberDao;
import com.itheima.health.pojo.Member;
import com.itheima.health.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = MemberService.class)
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    /**
     * 通过手机号查询会员是否存在
     *
     * @param telephone
     * @return
     */
    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    /**
     * 添加会员
     *
     * @param member
     */
    @Override
    public void add(Member member) {
        memberDao.add(member);
    }

    /**
     * 统计过去一年的会员总数
     *
     * @param months
     * @return
     */
    @Override
    public List<Integer> getMemberReport(List<String> months) {
        List<Integer> list = new ArrayList<>();
        months.forEach(month -> {
            // 查询到每个月最后一天为止的会员总数量
            list.add(memberDao.findMemberCountBeforeDate(month + "-31"));
        });
        return list;
    }

    /**
     * 性别分类占比
     * @return
     */
    @Override
    public List<Map<String, Object>> getSetmealReportBySex() {
        return memberDao.getSetmealReportBySex();
    }

    /**
     * 年龄段分类占比
     * @return
     */
    @Override
    public List<Map<String, Object>> getSetmealReportByAge() {
        return memberDao.getSetmealReportByAge();
    }

    /**
     * 按日历查询会员数量
     * @param months
     * @return
     */
    @Override
    public List<Integer> getMemberReport1(List<String> months) {
        List<Integer> memberCount = new ArrayList<>();
        months.forEach(month -> {
            // 查询到每个月最后一天为止的会员总数量
            memberCount.add(memberDao.findMemberCountBeforeDate(month + "-31"));
        });
        return memberCount;
    }
}
