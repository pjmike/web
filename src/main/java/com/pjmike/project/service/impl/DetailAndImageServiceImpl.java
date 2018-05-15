package com.pjmike.project.service.impl;

import com.pjmike.project.domain.Details;
import com.pjmike.project.domain.Images;
import com.pjmike.project.repository.DetailRepository;
import com.pjmike.project.repository.ImageRepository;
import com.pjmike.project.service.DetailAndImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-10 22:34
 */
@Service
public class DetailAndImageServiceImpl implements DetailAndImageService {
    @Autowired
    private DetailRepository repository;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Details updateDetails(Details details) {
        Details details1 = repository.getOne(1);
        details1.setContent(details.getContent());
        return repository.save(details1);
    }

    @Override
    public Details find() {
        return repository.getOne(1);
    }

    @Override
    public Images save(Images images) {
        return imageRepository.save(images);
    }

    @Override
    public void delete(Integer id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Images update(Images images) {
        return imageRepository.save(images);
    }

    @Override
    public Images findOne(Integer id) {
        return imageRepository.getOne(id);
    }

    @Override
    public List<Images> findAll() {
        return imageRepository.findAll();
    }
}
