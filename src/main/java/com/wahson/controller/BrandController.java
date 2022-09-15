package com.wahson.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wahson.dao.BrandDao;
import com.wahson.domain.Brand;
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

        IPage<Brand> resultPage = brandService.selectByPageAndCondition(currentPage, pageSize, brand);
        // 执行删除时，若最后一页只有一条数据，把他删了后，页面会到当前的最后一页，数据也要变为最后一页的数据，否则最后一页显示第一页数据。
        if (currentPage > resultPage.getPages()) {
            resultPage = brandService.selectByPageAndCondition((int) resultPage.getPages(), pageSize, brand);
        }
        List<Brand> resultPageRecords = resultPage.getRecords();
        Integer totalCount = Math.toIntExact(resultPage.getTotal());
        boolean flag = resultPageRecords.size() > 0;
        return new Result(flag ? Code.GET_OK : Code.GET_ERR, resultPageRecords, totalCount);
    }

    // 该方法为测试方法，因要考虑页码，条件查询及每页显示的数量，故实际运行时并不会调用
    // 查 GET
    @GetMapping
    public Result selectAll() {
        List<Brand> brandList = brandService.selectAll();
        Integer code = brandList != null ? Code.SAVE_OK : Code.SAVE_ERR;
        return new Result(code, brandList);
    }


}

