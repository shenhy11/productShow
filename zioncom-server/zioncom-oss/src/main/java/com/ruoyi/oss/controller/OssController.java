package com.ruoyi.oss.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.oss.domain.OssFile;
import com.ruoyi.oss.service.IOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OSS 文件服务 控制器
 */
@RestController
@RequestMapping("/api/oss")
public class OssController extends BaseController {

    @Autowired
    private IOssService ossService;

    /**
     * 上传文件
     *
     * @param file 文件实体
     * @param category 业务分类 (image/firmware/document/other)
     * @return 结果
     */
    @Log(title = "OSS文件管理", businessType = BusinessType.INSERT)
    @PreAuthorize("@ss.hasPermi('system:oss:upload')")
    @PostMapping("/upload")
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file, 
                                 @RequestParam(value = "category", defaultValue = "other") String category) {
        if (file.isEmpty()) {
            return AjaxResult.error("上传文件不能为空");
        }
        
        // 校验大小
        long size = file.getSize();
        if ("image".equals(category) && size > 5 * 1024 * 1024) {
             return AjaxResult.error("图片大小不能超过 5MB");
        }
        if ("firmware".equals(category) && size > 100 * 1024 * 1024) {
             return AjaxResult.error("固件文件大小不能超过 100MB");
        }

        OssFile uploaded = ossService.uploadFile(file, category);
        
        // 返回结果适配前端富文本编辑器和一般上传组件
        Map<String, Object> map = new HashMap<>();
        map.put("id", uploaded.getId());
        map.put("url", uploaded.getFileUrl());
        map.put("fileName", uploaded.getFileName());
        map.put("objectKey", uploaded.getObjectKey());
        
        return AjaxResult.success("上传成功", map);
    }

    /**
     * 获取文件列表
     */
    @PreAuthorize("@ss.hasPermi('system:oss:list')")
    @GetMapping("/list")
    public TableDataInfo list(@RequestParam(value = "category", required = false) String category) {
        startPage();
        List<OssFile> list = ossService.selectFileList(category);
        return getDataTable(list);
    }

    /**
     * 根据 ID 获取文件预签名下载地址
     */
    @GetMapping("/download/{id}")
    public AjaxResult getDownloadUrl(@PathVariable Long id) {
        String url = ossService.getDownloadUrl(id);
        Map<String, String> map = new HashMap<>();
        map.put("url", url);
        return AjaxResult.success(map);
    }

    /**
     * 删除文件记录及 OSS 上的实体文件
     */
    @Log(title = "OSS文件管理", businessType = BusinessType.DELETE)
    @PreAuthorize("@ss.hasPermi('system:oss:remove')")
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id) {
        return toAjax(ossService.deleteFile(id));
    }
}
