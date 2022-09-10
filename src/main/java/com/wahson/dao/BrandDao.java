package com.wahson.dao;

import com.wahson.domain.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author GeorgeWahson
 * @since 2022-09-08
 */
@Mapper
public interface BrandDao extends BaseMapper<Brand> {

}
