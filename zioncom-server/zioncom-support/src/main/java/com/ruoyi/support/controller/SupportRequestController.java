package com.ruoyi.support.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.support.domain.SupportRequest;
import com.ruoyi.support.service.ISupportRequestService;
@RestController
@RequestMapping("/api/support/support/request")
public class SupportRequestController extends BaseController {
    @Autowired private ISupportRequestService service;
    @GetMapping("/list")
    public TableDataInfo list(SupportRequest param) { startPage(); List<SupportRequest> list = service.selectList(param); return getDataTable(list); }
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) { return AjaxResult.success(service.selectById(id)); }
    @PostMapping
    public AjaxResult add(@RequestBody SupportRequest param) { return toAjax(service.insert(param)); }
    @PutMapping
    public AjaxResult edit(@RequestBody SupportRequest param) { return toAjax(service.update(param)); }
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) { return toAjax(service.deleteById(id)); }
}
