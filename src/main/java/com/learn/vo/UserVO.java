package com.learn.vo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author wangxl
 * @date 2021/7/23 13:12
 */
@Data
public class UserVO {
    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotNull(message = "年龄不能为null")
    @Min(value = 1, message = "年龄最小为 0 岁")
    @Max(value = 150, message = "年龄最大为 150 岁")
    private Integer age;
}
