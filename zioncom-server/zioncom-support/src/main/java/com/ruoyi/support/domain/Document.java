package com.ruoyi.support.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
public class Document extends BaseEntity {
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    private Long productId;
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    private String docType;
    public String getDocType() { return docType; }
    public void setDocType(String docType) { this.docType = docType; }
    private String titleZh;
    public String getTitleZh() { return titleZh; }
    public void setTitleZh(String titleZh) { this.titleZh = titleZh; }
    private String titleEn;
    public String getTitleEn() { return titleEn; }
    public void setTitleEn(String titleEn) { this.titleEn = titleEn; }
    private String fileUrl;
    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    private String fileName;
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    private Long fileSize;
    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    private Integer sortOrder;
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    private Integer status;
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
