package com.ruoyi.support.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
public class Firmware extends BaseEntity {
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    private Long productId;
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    private String version;
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    private String fileUrl;
    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    private String fileName;
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    private Long fileSize;
    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    private String releaseNoteZh;
    public String getReleaseNoteZh() { return releaseNoteZh; }
    public void setReleaseNoteZh(String releaseNoteZh) { this.releaseNoteZh = releaseNoteZh; }
    private String releaseNoteEn;
    public String getReleaseNoteEn() { return releaseNoteEn; }
    public void setReleaseNoteEn(String releaseNoteEn) { this.releaseNoteEn = releaseNoteEn; }
    private Date releaseDate;
    public Date getReleaseDate() { return releaseDate; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
    private Integer status;
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
