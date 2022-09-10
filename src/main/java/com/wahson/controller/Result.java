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

    //
    private Integer totalCount;

    // 不带反馈信息的Result对象，操作成功时不用显示msg
    public Result(Integer code,Object data) {
        this.data = data;
        this.code = code;
    }

    public Result(Object data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    // TODO 成功后补注释
    public Result(Integer code, Object data, Integer totalCount) {
        this.data = data;
        this.code = code;
        this.totalCount = totalCount;
    }
}
