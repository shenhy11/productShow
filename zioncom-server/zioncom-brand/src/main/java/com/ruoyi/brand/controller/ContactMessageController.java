package com.ruoyi.brand.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.brand.domain.ContactMessage;
import com.ruoyi.brand.service.IContactMessageService;
@RestController
@RequestMapping("/api/brand/contact/message")
public class ContactMessageController extends BaseController {
    @Autowired private IContactMessageService service;
    @GetMapping("/list")
    public TableDataInfo list(ContactMessage param) { startPage(); List<ContactMessage> list = service.selectList(param); return getDataTable(list); }
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) { return AjaxResult.success(service.selectById(id)); }
    @PostMapping
    public AjaxResult add(@RequestBody ContactMessage param) { return toAjax(service.insert(param)); }
    @PutMapping
    public AjaxResult edit(@RequestBody ContactMessage param) { return toAjax(service.update(param)); }
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) { return toAjax(service.deleteById(id)); }
}
