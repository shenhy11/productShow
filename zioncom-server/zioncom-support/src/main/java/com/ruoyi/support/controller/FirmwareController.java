package com.ruoyi.support.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.support.domain.Firmware;
import com.ruoyi.support.service.IFirmwareService;
@RestController
@RequestMapping("/api/support/firmware")
public class FirmwareController extends BaseController {
    @Autowired private IFirmwareService service;
    @GetMapping("/list")
    public TableDataInfo list(Firmware param) { startPage(); List<Firmware> list = service.selectList(param); return getDataTable(list); }
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) { return AjaxResult.success(service.selectById(id)); }
    @PostMapping
    public AjaxResult add(@RequestBody Firmware param) { return toAjax(service.insert(param)); }
    @PutMapping
    public AjaxResult edit(@RequestBody Firmware param) { return toAjax(service.update(param)); }
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) { return toAjax(service.deleteById(id)); }
}
