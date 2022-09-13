package com.wahson.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 获取description字段值
        String description = (String) getFieldValByName("description", metaObject);
        // 字段为空，可以进行填充
        // Object description不能直接 == null进行比较，否则前端页面及时没输入结果也为false
        if ("".equals(description.trim())) {
            setFieldValByName("description", "该公司很懒，没有留下简介信息！", metaObject);
        }
    }

    /**
     * 更新update同理，setFieldValByName为通用填充
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.insertFill(metaObject);
    }
}

