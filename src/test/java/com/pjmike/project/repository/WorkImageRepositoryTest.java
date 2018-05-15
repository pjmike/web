package com.pjmike.project.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WorkImageRepositoryTest {
    @Autowired
    private WorkImageRepository repository;
    @Test
    public void deleteByIdAndWorkId() {
        repository.deleteByIdAndWorkId(3,1);
    }
}