package com.pjmike.project.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author pjmike
 * @create 2018-05-10 21:14
 */
@Data
@Entity
@DynamicUpdate
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contactId;
    private String content1;
    private String content2;
    private String content3;
    private String content4;
    private String content5;
    private String imageUrl;
}
