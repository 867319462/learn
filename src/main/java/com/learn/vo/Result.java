package com.learn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回信息
 *
 * @author wangxl
 * @date 2021/7/23 22:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 数据
     */
    private Object data;
}
