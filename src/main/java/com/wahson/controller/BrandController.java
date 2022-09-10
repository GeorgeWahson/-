package com.wahson.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wahson.dao.BrandDao;
import com.wahson.domain.Brand;
import com.wahson.service.IBrandService;
import com.wahson.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author GeorgeWahson
 * @since 2022-09-08
 */
@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private BrandServiceImpl brandService;

    // 增 POST
    @PostMapping
    public Result save(@RequestBody Brand brand) {
        boolean flag = brandService.addBrand(brand);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    // 查 GET
    // brand对象从前端请求体RequestBody中传来
    @GetMapping
    public Result selectAll() {
        List<Brand> brandList = brandService.selectAll();
        Integer code = brandList != null ? Code.SAVE_OK : Code.SAVE_ERR;
        return new Result(code, brandList);
    }

    // 删 DELETE 单个
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        boolean flag = brandService.deleteByid(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    // 删 PATCH 批量删
    @PatchMapping
    public Result deleteByIds(@RequestBody Integer[] ids) {
        boolean flag = brandService.deleteByIds(ids);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    // 改 PUT 更新数据，通过id
    @PutMapping
    public Result updateBrand(@RequestBody Brand brand) {

        boolean flag = brandService.updateBrand(brand);
        System.out.println("flag" + flag);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    /** POST
     * 按条件搜索，传入搜索的对象
     * （brandName, companyName, status零个或多个有值），
     * 以及当前页数及每页显示条数
     * @param brand
     * @param currentPage
     * @param pageSize
     * @return
     */
    @PostMapping("/{currentPage}/{pageSize}")
    public Result selectByPageAndCondition (@PathVariable Integer currentPage, @PathVariable Integer pageSize, @RequestBody(required = false) Brand brand) {

        /*{
            "id": 2,
            "brandName": "华位",
            "companyName": "华为科技有限公司",
            "ordered": 100,
            "description": "以行践言，华为。",
            "status": 1
        }*/
        IPage<Brand> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<Brand> lqw = new LambdaQueryWrapper<Brand>();
        // 当值不存在时，不添加该搜索条件
        // 第一个为搜索条件，即值不为空。第二个参数为数据库列名称，第三个为用于搜索的值，
        // like("name", "王")--->name like '%王%'
        lqw.like(null != brand.getBrandName(), Brand::getBrandName, brand.getBrandName());
        lqw.like(null != brand.getCompanyName(), Brand::getCompanyName, brand.getCompanyName());
        lqw.like(null != brand.getStatus(), Brand::getStatus, brand.getStatus());
        IPage<Brand> resultPage = brandDao.selectPage(page, lqw);
        List<Brand> resultPageRecords = resultPage.getRecords();
        Integer totalCount = Math.toIntExact(resultPage.getTotal());
        boolean flag = resultPageRecords.size() > 0;
        return new Result(flag ? Code.GET_OK : Code.GET_ERR, resultPageRecords, totalCount);
    }


}

