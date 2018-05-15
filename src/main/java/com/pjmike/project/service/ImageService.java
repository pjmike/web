package com.pjmike.project.service;

import com.pjmike.project.domain.WorkImages;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-10 19:38
 */
public interface ImageService {
    /**
     * 返回所有图片URL
     *
     * @return
     */
    List<WorkImages> findAllImages();

    /**
     * 更新图片URL
     *
     * @param workImages
     * @return
     */
    WorkImages updateImage(WorkImages workImages);

    /**
     * 删除图片
     *
     * @param id
     * @return
     */
    void deleteImageByWorkIdAndId(Integer id,Integer workId);

    /**
     * 找出模块里的所有图片
     *
     * @param workId
     * @return
     */
    List<WorkImages> findImagesByWorkId(Integer workId);

    /**
     * 通过id和workId查询
     *
     * @param id
     * @param workId
     * @return
     */
    WorkImages findImageByIdAndWorkId(Integer id, Integer workId);

    /**
     * 添加图片
     *
     * @param images
     * @return
     */
    WorkImages addImage(WorkImages images);
}
