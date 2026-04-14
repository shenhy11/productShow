package com.ruoyi.support.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.support.domain.Faq;
import com.ruoyi.support.service.IFaqService;
@RestController
@RequestMapping("/api/support/faq")
public class FaqController extends BaseController {
    @Autowired private IFaqService service;
    @GetMapping("/list")
    public TableDataInfo list(Faq param) { startPage(); List<Faq> list = service.selectList(param); return getDataTable(list); }
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) { return AjaxResult.success(service.selectById(id)); }
    @PostMapping
    public AjaxResult add(@RequestBody Faq param) { return toAjax(service.insert(param)); }
    @PutMapping
    public AjaxResult edit(@RequestBody Faq param) { return toAjax(service.update(param)); }
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) { return toAjax(service.deleteById(id)); }
}
