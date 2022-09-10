package com.wahson;

import com.wahson.dao.BrandDao;
import com.wahson.domain.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InfoSystemApplicationTests {


    @Autowired
    private BrandDao brandDao;

    @Test
    void testSelectById() {
        Brand brand = brandDao.selectById(2);
        System.out.println(brand);
    }
}
