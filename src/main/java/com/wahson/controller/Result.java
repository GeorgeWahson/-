package com.wahson.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 将
 * 所有操作的结果数据、
 * 操作状态码、
 * 反馈信息封
 * 装在Result中
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Object data;

    private Integer code;

    private String msg;

    // 不带反馈信息的Result对象
    public Result(Integer code,Object data) {
        this.data = data;
        this.code = code;
    }
}
