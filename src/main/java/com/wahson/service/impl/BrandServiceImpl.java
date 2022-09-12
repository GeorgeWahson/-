package com.wahson.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wahson.controller.Result;
import com.wahson.domain.Brand;
import com.wahson.dao.BrandDao;
import com.wahson.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private BrandDao brandDao;

    // 增
    public boolean addBrand(Brand brand) {
        int insert = brandDao.insert(brand);
        return insert == 1;
    }

    // 单个删除，前端传入所选数据的id，按id删除
    public boolean deleteByid(Integer id) {
        int deleteNum = brandDao.deleteById(id);
        return deleteNum == 1;
    }

    // 批量删除，前端页面传入被勾选的数据id所组成的id数组
    public boolean deleteByIds(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        int deleteNum = brandDao.deleteBatchIds(list);
        return deleteNum == list.size();
    }


    // 改 前端页面传入修改后数据转入brand中进行修改
    public boolean updateBrand(Brand brand) {
        System.out.println("service: " + brand);
        int update = brandDao.updateById(brand);
        System.out.println("update number: " + update);
        return update == 1;
    }

    // 按条件查询，初始加载此方法
    public IPage<Brand> selectByPageAndCondition (Integer currentPage, Integer pageSize, Brand brand) {
        IPage<Brand> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<Brand> lqw = new LambdaQueryWrapper<Brand>();
        // 当值不存在时，不添加该搜索条件
        // 第一个为搜索条件，即值不为空。第二个参数为数据库列名称，第三个为用于搜索的值，
        // like("name", "王")--->name like '%王%'
        lqw.like(null != brand.getBrandName(), Brand::getBrandName, brand.getBrandName());
        lqw.like(null != brand.getCompanyName(), Brand::getCompanyName, brand.getCompanyName());
        lqw.like(null != brand.getStatus(), Brand::getStatus, brand.getStatus());
        IPage<Brand> resultPage = brandDao.selectPage(page, lqw);
        return resultPage;
    }

    // 该方法仅为测试，实际查询方法为selectByPageAndCondition
    // 查，查询全部，查询结果应不为空
    public List<Brand> selectAll() {
        List<Brand> brandList = brandDao.selectList(null);
        return brandList;
    }



}
