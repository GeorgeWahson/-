package com.wahson;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wahson.dao.BrandDao;
import com.wahson.domain.Brand;
import com.wahson.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class InfoSystemApplicationTests {

    // TODO 建立测试环境，Test删除数据库
    @Autowired
    private BrandDao brandDao;

    @Autowired
    private BrandServiceImpl brandService;

    @Test
    void testSelectById() {
        Brand brand = new Brand();
        IPage<Brand> resultPage = brandService.selectByPageAndCondition(1, 10, brand);
        System.out.println("==========每页显示数量为：" + resultPage.getSize() + "==========");
        System.out.println("=============当前为第" + resultPage.getCurrent() + "页=============");
        System.out.println("查询结果数据总量为：" + resultPage.getTotal());
        System.out.println("查询结果总页数为：" + resultPage.getPages());
        System.out.println("查询结果如下：");
        List<Brand> records = resultPage.getRecords();
        for (Brand record : records) {
            System.out.println(record);
        }
    }

    @Test
    void testSelectList() {
        List<Brand> brandList = brandService.selectAll();
        for (Brand brand : brandList) {
            System.out.println(brand);
        }
    }

}
