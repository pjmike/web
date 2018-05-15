package com.pjmike.project.service.impl;

import com.pjmike.project.domain.WorkImages;
import com.pjmike.project.repository.WorkImageRepository;
import com.pjmike.project.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-10 19:40
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private WorkImageRepository workImageRepository;
    @Override
    public List<WorkImages> findAllImages() {
        return workImageRepository.findAll();
    }

    @Override
    public WorkImages updateImage(WorkImages workImages) {
        return workImageRepository.save(workImages);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteImageByWorkIdAndId(Integer id,Integer workId) {
        workImageRepository.deleteByIdAndWorkId(id,workId);
    }


    @Override
    public List<WorkImages> findImagesByWorkId(Integer workId) {
        return workImageRepository.findByWorkId(workId);
    }

    @Override
    public WorkImages findImageByIdAndWorkId(Integer id, Integer workId) {
        return workImageRepository.findByIdAndWorkId(id, workId);
    }

    @Override
    public WorkImages addImage(WorkImages images) {
        return workImageRepository.save(images);
    }
}
