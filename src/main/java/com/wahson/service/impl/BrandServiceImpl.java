package com.wahson.service.impl;

import com.wahson.domain.Brand;
import com.wahson.dao.BrandDao;
import com.wahson.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        System.out.println("list.size:" + list.size());
        int deleteNum = brandDao.deleteBatchIds(list);
        System.out.println("deleteNum: " + deleteNum);
        return deleteNum == list.size();
    }

    // 查，查询全部，查询结果应不为空
    public List<Brand> selectAll() {
        List<Brand> brandList = brandDao.selectList(null);
        return brandList;
    }



    // 改 前端页面传入修改后数据转入brand中进行修改
    public boolean updateBrand(Brand brand) {
        System.out.println("service: " + brand);
        int update = brandDao.updateById(brand);
        System.out.println("update number: " + update);
        return update == 1;
    }


}
