package com.pjmike.project.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * enum
 *
 * @author pjmike
 * @create 2018-05-02 19:50
 */
@AllArgsConstructor
public enum  ExceptionEnum {
    OBJECT_NOT_FOUND("资源不存在"),
    IMG_IS_NULL("图片为空");
    @Getter
    private String msg;
}
