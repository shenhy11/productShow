package com.ruoyi.support.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.support.domain.TechVideo;
import com.ruoyi.support.service.ITechVideoService;
@RestController
@RequestMapping("/api/support/tech/video")
public class TechVideoController extends BaseController {
    @Autowired private ITechVideoService service;
    @GetMapping("/list")
    public TableDataInfo list(TechVideo param) { startPage(); List<TechVideo> list = service.selectList(param); return getDataTable(list); }
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) { return AjaxResult.success(service.selectById(id)); }
    @PostMapping
    public AjaxResult add(@RequestBody TechVideo param) { return toAjax(service.insert(param)); }
    @PutMapping
    public AjaxResult edit(@RequestBody TechVideo param) { return toAjax(service.update(param)); }
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) { return toAjax(service.deleteById(id)); }
}
