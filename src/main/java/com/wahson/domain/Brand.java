package com.wahson.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author GeorgeWahson
 * @since 2022-09-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("tb_brand")
// ActiveRecord的主要思想是： 每一个数据库表对应创建一个类，类的每一个对象实例对应于数据库中表的一行记录；通常表的每个字段 在类中都有相应的Field；
//        ActiveRecord同时负责把自己持久化，在ActiveRecord中封装了对数据库的访问，即CRUD;；
//        ActiveRecord是一种领域模型(Domain Model)，封装了部分业务逻辑；
// 底层还是需要Mapper的。即BrandDao，测试时需要注入
// public class Brand extends Model<Brand> implements Serializable{
public class Brand implements Serializable{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "brand_name")
    private String brandName;

    // 解决与数据库命名不一致(类似resultMap)
    @TableField(value = "company_name")
    private String companyName;

    private Integer ordered;

    // 插入和更新时自动填充字段（当 description 为空时）
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String description;

    private Integer status;


    /*
        @TableField(select = false)
        不能加 select = false,否则更新数据时不会sql语句不会添加SET version = ?
    */
    @Version
    private Integer version;

    @TableField(select = false) // 不参与查询
    @TableLogic
    private Integer deleted;



    // //逻辑视图
    public String getStatusStr(){
        if (status == null){
            return "未知";
        }
        return status == 0 ? "禁用":"启用";
    }

    /**
     * 考虑范围查询的话，可以定义个类继承Brand, @Data后定义个maxOrdered
     */

}
