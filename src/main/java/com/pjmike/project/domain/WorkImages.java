package com.pjmike.project.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 图片页
 *
 * @author pjmike
 * @create 2018-05-10 19:37
 */
@Data
@Entity
@DynamicUpdate
public class WorkImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer workId;
    private String imageUrl;
}
