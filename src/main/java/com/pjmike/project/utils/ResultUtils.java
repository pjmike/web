package com.pjmike.project.utils;

/**
 * 封装类
 *
 * @author pjmike
 * @create 2018-04-26 16:45
 */
public class ResultUtils {
    public static ResponseResult success(Object object) {
        ResponseResult result = new ResponseResult();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static ResponseResult success() {
        return success(null);
    }

    public static ResponseResult error(Integer code, String msg) {
        return new ResponseResult(code,msg);
    }
}
