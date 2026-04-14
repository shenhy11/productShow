package com.ruoyi.oss.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * OSS 文件记录实体
 * 对应数据库表 zc_oss_file
 */
public class OssFile extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 文件ID */
    private Long id;

    /** 原始文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** OSS 对象 Key（如 images/2024/cover.jpg）*/
    private String objectKey;

    /** 文件访问 URL（CDN 地址）*/
    @Excel(name = "文件URL")
    private String fileUrl;

    /** 文件大小（字节）*/
    @Excel(name = "文件大小(字节)")
    private Long fileSize;

    /** 文件 MIME 类型（如 image/jpeg）*/
    private String fileType;

    /** 文件分类（image/firmware/document/other）*/
    @Excel(name = "文件分类")
    private String category;

    // ===== Getter / Setter =====

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getObjectKey() { return objectKey; }
    public void setObjectKey(String objectKey) { this.objectKey = objectKey; }

    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }

    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return "OssFile{id=" + id + ", fileName='" + fileName + "', fileUrl='" + fileUrl + "', category='" + category + "'}";
    }
}
