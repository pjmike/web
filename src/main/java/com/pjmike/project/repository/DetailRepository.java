package com.pjmike.project.repository;

import com.pjmike.project.domain.Details;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pjmike
 * @create 2018-05-10 22:30
 */
public interface DetailRepository extends JpaRepository<Details,Integer> {

}
