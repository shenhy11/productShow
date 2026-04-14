package com.ruoyi.content.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.content.domain.Article;
import com.ruoyi.content.service.IArticleService;
@RestController
@RequestMapping("/api/content/article")
public class ArticleController extends BaseController {
    @Autowired private IArticleService service;
    @GetMapping("/list")
    public TableDataInfo list(Article param) { startPage(); List<Article> list = service.selectList(param); return getDataTable(list); }
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) { return AjaxResult.success(service.selectById(id)); }
    @PostMapping
    public AjaxResult add(@RequestBody Article param) { return toAjax(service.insert(param)); }
    @PutMapping
    public AjaxResult edit(@RequestBody Article param) { return toAjax(service.update(param)); }
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) { return toAjax(service.deleteById(id)); }
}
