package com.pjmike.project.repository;

import com.pjmike.project.domain.WorkImages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-10 19:38
 */
public interface WorkImageRepository extends JpaRepository<WorkImages,Integer> {
    /**
     * 通过workId查询图片列表
     *
     * @param workId
     * @return
     */
    List<WorkImages> findByWorkId(Integer workId);

    /**
     * 删除图片
     *
     * @param id
     * @param workId
     */
    void deleteByIdAndWorkId(Integer id, Integer workId);

    /**
     * 根据workId和Id查询
     *
     * @param id
     * @param workId
     * @return
     */
    WorkImages findByIdAndWorkId(Integer id, Integer workId);
}
