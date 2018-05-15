package com.pjmike.project.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 首页更改内容
 *
 * @author pjmike
 * @create 2018-04-25 22:34
 */
@Entity
@Data
@DynamicUpdate
public class Home implements Serializable{

    private static final long serialVersionUID = 2296762524984890660L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer homeId;
    private String content1;
    private String content2;
    private String content3;
    private String content4;
    private String img;
    private String rgba;

    public Home() {
    }

    public Home(Integer homeId, String img) {
        this.homeId = homeId;
        this.img = img;
    }
}
