package com.pjmike.project.repository;

import com.pjmike.project.domain.Home;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-04-25 22:45
 */
public interface HomeRepository extends JpaRepository<Home,Integer>{
    Home findByHomeId(Integer homeId);

    List<Home> findAll(Sort sort);

}
