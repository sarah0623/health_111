package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.Member;

import java.util.List;
import java.util.Map;

public interface MemberDao {
    /**
     * 查询所有
     *
     * @return
     */
    List<Member> findAll();

    Page<Member> selectByCondition(String queryString);

    void add(Member member);

    void deleteById(Integer id);

    Member findById(Integer id);

    /**
     * 通过手机号码查询
     *
     * @param telephone
     * @return
     */
    Member findByTelephone(String telephone);

    void edit(Member member);

    Integer findMemberCountBeforeDate(String date);

    Integer findMemberCountByDate(String date);

    Integer findMemberCountAfterDate(String date);

    Integer findMemberTotalCount();

    /**
     * 性别分类占比
     * @return
     */
    List<Map<String, Object>> getSetmealReportBySex();

    /**
     * 年龄段分类占比
     * @return
     */
    List<Map<String, Object>> getSetmealReportByAge();
}
