package com.pjmike.project.service.impl;

import com.pjmike.project.domain.Contact;
import com.pjmike.project.repository.CantactRepository;
import com.pjmike.project.service.IndexAndCantactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pjmike
 * @create 2018-05-10 21:42
 */
@Service
public class IndexAndCantactServiceImpl implements IndexAndCantactService {
    @Autowired
    private CantactRepository cantactRepository;
    @Override
    public Contact findCantact() {
        return cantactRepository.findByContactId(1);
    }

    @Override
    public Contact update(Contact contact) {
        return cantactRepository.save(contact);
    }
}
