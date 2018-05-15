package com.pjmike.project.service.impl;

import com.pjmike.project.domain.Works;
import com.pjmike.project.repository.WorkRepository;
import com.pjmike.project.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-04-26 20:12
 */
@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkRepository repository;
    @Override
    public Works save(Works work) {
        return repository.save(work);
    }

    @Override
    public Works findWorksById(Integer id) {
        return repository.findByWorkId(id);
    }

    @Override
    public Works updateWorks(Works work) {
        return repository.save(work);
    }

    @Override
    public List<Works> findAllWorks() {
        Sort sort = new Sort(Sort.Direction.ASC,"workId");
        return repository.findAll(sort);
    }

    @Override
    public void delete(Integer id) {
        Works works = repository.findByWorkId(id);
        repository.delete(works);
    }
}
