package com.ruoyi.product.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.product.domain.ProductCategory;
import com.ruoyi.product.service.IProductCategoryService;
@RestController
@RequestMapping("/api/product/product/category")
public class ProductCategoryController extends BaseController {
    @Autowired private IProductCategoryService service;
    @GetMapping("/list")
    public TableDataInfo list(ProductCategory param) { startPage(); List<ProductCategory> list = service.selectList(param); return getDataTable(list); }
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) { return AjaxResult.success(service.selectById(id)); }
    @PostMapping
    public AjaxResult add(@RequestBody ProductCategory param) { return toAjax(service.insert(param)); }
    @PutMapping
    public AjaxResult edit(@RequestBody ProductCategory param) { return toAjax(service.update(param)); }
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) { return toAjax(service.deleteById(id)); }
}
