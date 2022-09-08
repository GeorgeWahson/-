package com.wahson.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

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
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "book_name")
    private String brandName;

    // 解决与数据库命名不一致(类似resultMap)
    @TableField(value = "company_name")
    private String companyName;

    private Integer ordered;

    private String description;

    private Integer status;

    @TableField(select = false)
    @Version
    private Integer version;

    @TableField(select = false) // 不参与查询
    @TableLogic
    private Integer deleted;


}
