package com.pjmike.project.service;

import com.pjmike.project.domain.Details;
import com.pjmike.project.domain.Images;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-10 22:31
 */
public interface DetailAndImageService {
    /**
     *
     * @param details
     * @return
     */
    Details updateDetails(Details details);


    Details find();
    /**
     * 添加图片
     *
     * @param images
     * @return
     */
    Images save(Images images);

    /**
     * 删除图片
     *
     * @param id
     * @return
     */
    void delete(Integer id);

    /**
     * 修改图片
     *
     * @param images
     * @return
     */
    Images update(Images images);

    /**
     *
     * @param id
     * @return
     */
    Images findOne(Integer id);

    List<Images> findAll();
}
