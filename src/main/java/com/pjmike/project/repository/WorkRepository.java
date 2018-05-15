package com.pjmike.project.repository;

import com.pjmike.project.domain.Works;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-04-25 22:49
 */
public interface WorkRepository extends JpaRepository<Works,Integer>{

    Works findByWorkId(Integer workId);

    List<Works> findAll(Sort sort);

}
