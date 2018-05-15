package com.pjmike.project.service.impl;

import com.pjmike.project.domain.Works;
import com.pjmike.project.service.WorkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkServiceImplTest {
    @Autowired
    private WorkService workService;
    @Test
    public void findWorksById() {
        List<Works> works = workService.findAllWorks();
        System.out.println(works);
    }
}