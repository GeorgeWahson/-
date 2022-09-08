package com.wahson.service.impl;

import com.wahson.domain.Brand;
import com.wahson.dao.BrandDao;
import com.wahson.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GeorgeWahson
 * @since 2022-09-08
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandDao, Brand> implements IBrandService {

}
