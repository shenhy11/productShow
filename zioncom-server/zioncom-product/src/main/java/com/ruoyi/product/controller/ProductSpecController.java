package com.ruoyi.product.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.product.domain.ProductSpec;
import com.ruoyi.product.service.IProductSpecService;
@RestController
@RequestMapping("/api/product/product/spec")
public class ProductSpecController extends BaseController {
    @Autowired private IProductSpecService service;
    @GetMapping("/list")
    public TableDataInfo list(ProductSpec param) { startPage(); List<ProductSpec> list = service.selectList(param); return getDataTable(list); }
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) { return AjaxResult.success(service.selectById(id)); }
    @PostMapping
    public AjaxResult add(@RequestBody ProductSpec param) { return toAjax(service.insert(param)); }
    @PutMapping
    public AjaxResult edit(@RequestBody ProductSpec param) { return toAjax(service.update(param)); }
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) { return toAjax(service.deleteById(id)); }
}
