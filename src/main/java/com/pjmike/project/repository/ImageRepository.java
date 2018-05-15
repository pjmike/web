package com.pjmike.project.repository;

import com.pjmike.project.domain.Images;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pjmike
 * @create 2018-05-10 22:29
 */
public interface ImageRepository extends JpaRepository<Images,Integer> {
    @Override
    void deleteById(Integer id);
}
