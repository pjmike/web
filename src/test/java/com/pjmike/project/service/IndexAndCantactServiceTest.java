package com.pjmike.project.service;

import com.pjmike.project.domain.Contact;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IndexAndCantactServiceTest {
    @Autowired
    private IndexAndCantactService indexAndCantactService;
    @Test
    public void findCantact() {
//        Assert.assertNotNull(indexAndCantactService.findCantact());
        Contact contact = indexAndCantactService.findCantact();
        log.info("result:{}",contact);
    }
}