package com.pjmike.project.utils;

import lombok.Data;

/**
 * 响应结果类
 *
 * @author pjmike
 * @create 2018-04-26 16:42
 */
@Data
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体信息
     */
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
