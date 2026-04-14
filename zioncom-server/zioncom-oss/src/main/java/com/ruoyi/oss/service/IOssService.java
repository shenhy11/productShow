package com.ruoyi.oss.service;

import com.ruoyi.oss.domain.OssFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * OSS 文件服务接口
 */
public interface IOssService {

    /**
     * 上传文件到 OSS
     *
     * @param file     上传的文件对象
     * @param category 业务分类（image, firmware, document, other）
     * @return 上传成功后的实体（含 URL 和 ObjectKey）
     */
    OssFile uploadFile(MultipartFile file, String category);

    /**
     * 根据 文件ID 获取预签名下载 URL（处理私有 Bucket 场景）
     *
     * @param id 文件ID
     * @return 预签名 URL 字符串
     */
    String getDownloadUrl(Long id);

    /**
     * 删除 OSS 文件及其数据库记录
     *
     * @param id 文件ID
     * @return 结果
     */
    int deleteFile(Long id);

    /**
     * 查询文件列表
     *
     * @param category 分类（可选）
     * @return 文件集合
     */
    List<OssFile> selectFileList(String category);
}
