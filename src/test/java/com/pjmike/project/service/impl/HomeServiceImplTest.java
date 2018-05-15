package com.pjmike.project.service.impl;

import com.pjmike.project.domain.Home;
import com.pjmike.project.service.HomeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeServiceImplTest {
    @Autowired
    private HomeService homeService;
    @Test
    public void findAll() {
        List<Home> homes = homeService.findAll();
        System.out.println(homes);
    }
}