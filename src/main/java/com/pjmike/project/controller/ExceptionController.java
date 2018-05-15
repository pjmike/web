package com.pjmike.project.controller;

import com.pjmike.project.exception.ImgException;
import com.pjmike.project.exception.ObjectException;
import com.pjmike.project.utils.ResponseResult;
import com.pjmike.project.utils.ResultUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author pjmike
 * @create 2018-05-02 19:44
 */
@ControllerAdvice
public class ExceptionController {
    /**
     * 返回404
     *
     * @param o
     * @return
     */
    @ExceptionHandler(ObjectException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseResult ObjectExceptionHandler(ObjectException o) {
        return ResultUtils.error(1, o.getMessage());
    }

    /**
     * 返回500
     *
     * @param i
     * @return
     */
    @ExceptionHandler(ImgException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult ImgExceptionHandler(ImgException i) {
        return ResultUtils.error(1, i.getMessage());
    }
}
