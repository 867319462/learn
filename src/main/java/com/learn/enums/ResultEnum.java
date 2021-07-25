package com.learn.enums;

import com.learn.vo.Result;

/**
 * 返回值枚举
 *
 * @author wangxl
 * @date 2021/7/23 22:27
 */
public enum ResultEnum {
    /**
     * 参数错误
     */
    PARAM_ERROR(101, "参数错误"),

    /**
     * token 过期
     */
    TOKEN_EXPIRED(201, "token 已过期"),

    /**
     * 未知错误
     */
    UNKNOWN_EXCEPTION(1000, "未知错误");

    /**
     * 响应值
     */
    private final Integer code;

    /**
     * 响应信息
     */
    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回信息
     *
     * @param resultEnum 返回值枚举
     * @return 返回信息
     */
    public static Result setInfo(ResultEnum resultEnum) {
        return setInfo(resultEnum, null);
    }

    /**
     * 返回信息
     *
     * @param resultEnum 返回值枚举
     * @param obj        数据
     * @return 返回信息
     */
    public static Result setInfo(ResultEnum resultEnum, Object obj) {
        return new Result(resultEnum.code, resultEnum.msg, obj);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
