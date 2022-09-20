package com.wahson.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wahson.domain.Brand;
import com.wahson.dao.BrandDao;
import com.wahson.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    // spring-admin计数器 Counter 包为 io.micrometer.core.instrument
    private Counter select_count;
    private Counter add_count;
    private Counter update_count;
    private Counter delete_times;

    // 构造器传入
    public BrandServiceImpl (MeterRegistry meterRegistry) {
        select_count = meterRegistry.counter("用户调用查询次数：");
        add_count = meterRegistry.counter("用户调用添加次数：");
        update_count = meterRegistry.counter("用户调用更新次数：");
        delete_times = meterRegistry.counter("用户调用删除次数：");
    }

    // 增
    public boolean addBrand(Brand brand) {
        int insert = brandDao.insert(brand);
        add_count.increment();
        return insert == 1;
    }

    // 单个删除，前端传入所选数据的id，按id删除
    public boolean deleteByid(Integer id) {
        int deleteNum = brandDao.deleteById(id);
        delete_times.increment();
        return deleteNum == 1;
    }

    // 批量删除，前端页面传入被勾选的数据id所组成的id数组
    public boolean deleteByIds(Integer[] ids) {
        List<Integer> list = Arrays.asList(ids);
        int deleteNum = brandDao.deleteBatchIds(list);
        delete_times.increment();
        return deleteNum == list.size();
    }


    /**
     * 修改数据，并引用MP的乐观锁插件
     * 因此，不能直接用前端页面传来的brand进行更新：brandDao.updateById(brand)
     * 要先根据id查询获得原对象Object1，再依次setXXX,最后updateById(Object1)......
     * @param brand
     * @return
     */

    public boolean updateBrand(Brand brand) {

        Brand brandVersion = brandDao.selectById(brand.getId());

        brandVersion.setBrandName(brand.getBrandName());
        brandVersion.setCompanyName(brand.getCompanyName());
        brandVersion.setOrdered(brand.getOrdered());
        brandVersion.setStatus(brand.getStatus());
        brandVersion.setDescription(brand.getDescription());

        int update = brandDao.updateById(brandVersion);
        update_count.increment();
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
        // 调用方法后counter自增
        select_count.increment();
        return resultPage;
    }

    // 该方法仅为测试，实际查询方法为selectByPageAndCondition
    // 查，查询全部，查询结果应不为空
    public List<Brand> selectAll() {
        List<Brand> brandList = brandDao.selectList(null);
        return brandList;
    }



}
