package com.pjmike.project.domain.dto;

import com.pjmike.project.domain.Details;
import com.pjmike.project.domain.Images;
import lombok.Data;

import java.util.List;

/**
 * @author pjmike
 * @create 2018-05-10 22:19
 */
@Data
public class DetailDTO {
    private String content;
    List<Images> images;
}
