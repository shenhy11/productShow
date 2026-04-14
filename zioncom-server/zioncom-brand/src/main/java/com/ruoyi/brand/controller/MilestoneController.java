package com.ruoyi.brand.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.brand.domain.Milestone;
import com.ruoyi.brand.service.IMilestoneService;
@RestController
@RequestMapping("/api/brand/milestone")
public class MilestoneController extends BaseController {
    @Autowired private IMilestoneService service;
    @GetMapping("/list")
    public TableDataInfo list(Milestone param) { startPage(); List<Milestone> list = service.selectList(param); return getDataTable(list); }
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) { return AjaxResult.success(service.selectById(id)); }
    @PostMapping
    public AjaxResult add(@RequestBody Milestone param) { return toAjax(service.insert(param)); }
    @PutMapping
    public AjaxResult edit(@RequestBody Milestone param) { return toAjax(service.update(param)); }
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) { return toAjax(service.deleteById(id)); }
}
