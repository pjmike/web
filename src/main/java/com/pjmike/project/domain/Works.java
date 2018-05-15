package com.pjmike.project.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 小案例的文字
 *
 * @author pjmike
 * @create 2018-04-25 22:36
 */
@Entity
@Data
public class Works implements Serializable{
    private static final long serialVersionUID = -5074617963149683779L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workId;
    private String content1;
    private String content2;
    private String img;
    private String rgba;
}
