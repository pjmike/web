package com.pjmike.project.service.impl;

import com.pjmike.project.domain.Home;
import com.pjmike.project.repository.HomeRepository;
import com.pjmike.project.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-04-26 16:39
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeRepository repository;
    @Override
    public Home save(Home home) {
        return repository.save(home);
    }

    @Override
    public Home findHomeById(Integer id) {
        return repository.findByHomeId(id);
    }

    @Override
    public Home updateHome(Home home) {
        return repository.save(home);
    }

    @Override
    public List<Home> findAll() {
        Sort sort = new Sort(Sort.Direction.ASC,"homeId");
        return repository.findAll(sort);
    }
}
