package com.pjmike.project.service;


import com.pjmike.project.domain.Works;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-04-26 20:10
 */
public interface WorkService {
    /**
     * 插入
     *
     * @param work
     * @return
     */
    Works save(Works work);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Works findWorksById(Integer id);

    /**
     * 修改
     *
     * @param work
     * @return
     */
    Works updateWorks(Works work);

    /**
     * 找出所有
     *
     * @return
     */
    List<Works> findAllWorks();


    void delete(Integer id);
}
