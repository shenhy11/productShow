package com.ruoyi.product.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.product.domain.Product;
import com.ruoyi.product.service.IProductService;
@RestController
@RequestMapping("/api/product/product")
public class ProductController extends BaseController {
    @Autowired private IProductService service;
    @GetMapping("/list")
    public TableDataInfo list(Product param) { startPage(); List<Product> list = service.selectList(param); return getDataTable(list); }
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) { return AjaxResult.success(service.selectById(id)); }
    @PostMapping
    public AjaxResult add(@RequestBody Product param) { return toAjax(service.insert(param)); }
    @PutMapping
    public AjaxResult edit(@RequestBody Product param) { return toAjax(service.update(param)); }
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) { return toAjax(service.deleteById(id)); }
}
