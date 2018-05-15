package com.pjmike.project.service;

import com.pjmike.project.domain.Home;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-04-26 16:37
 */
public interface HomeService {
    /**
     * 插入
     *
     * @param home
     * @return
     */
    Home save(Home home);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Home findHomeById(Integer id);

    /**
     * 修改
     *
     * @param home
     * @return
     */
    Home updateHome(Home home);

    /**
     * 找出所有
     *
     * @return
     */
    List<Home> findAll();
}
