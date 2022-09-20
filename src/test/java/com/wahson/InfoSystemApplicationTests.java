package com.wahson;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wahson.dao.BrandDao;
import com.wahson.domain.Brand;
import com.wahson.service.impl.BrandServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@SpringBootTest
@Transactional // 设置事务，打包及测试时，测试数据不会加入数据库
@Rollback(value = true) // 默认回滚，但id还是会被占用
class InfoSystemApplicationTests {

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private BrandServiceImpl brandService;

    @Test
    void testSelectById() {
        Brand brand = new Brand();
        Integer count = brandDao.selectCount(null);
        IPage<Brand> resultPage = brandService.selectByPageAndCondition(1, 10, brand);
        log.info("==========每页显示数量为：" + resultPage.getSize() + "==========");
        log.info("=============当前为第" + resultPage.getCurrent() + "页=============");
        log.info("数据库共有" + count + "条数据（selectCount）");
        log.info("符合查询条件的数据有：" + resultPage.getTotal() + "条（getTotal）");
        log.info("查询结果总页数为：" + resultPage.getPages());
        log.info("查询结果如下：");
        List<Brand> records = resultPage.getRecords();
        for (Brand record : records) {
            System.out.println(record);
        }
//        log.warn("end");
//        log.error("ending...");
    }

    @Test
    void testSelectList() {
        List<Brand> brandList = brandService.selectAll();
        for (Brand brand : brandList) {
            System.out.println(brand);
        }
    }

    @Test
    void testActiveRecord() {
        // 不用brandDao brandService亦可以查询
        // 为了不冲突，原Brand.java中extends Model<User>已被注释，开启后即可测试
        Brand brand = new Brand();
        brand.setId(66);
//        Brand brand1 = brand.selectById();
//        System.out.println(brand1);
    }

    @Test
    void testInsert() {
        Brand brand = new Brand();
        brand.setStatus(1);
        brand.setBrandName("Maven test");
        brand.setCompanyName("Maven test");
        brand.setDescription("hello test");
        brand.setOrdered(100);
        boolean b = brandService.addBrand(brand);
        System.out.println(b);
    }

}
