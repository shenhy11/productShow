package com.ruoyi.support.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
public class TechVideo extends BaseEntity {
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    private Long productId;
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    private String titleZh;
    public String getTitleZh() { return titleZh; }
    public void setTitleZh(String titleZh) { this.titleZh = titleZh; }
    private String titleEn;
    public String getTitleEn() { return titleEn; }
    public void setTitleEn(String titleEn) { this.titleEn = titleEn; }
    private String descZh;
    public String getDescZh() { return descZh; }
    public void setDescZh(String descZh) { this.descZh = descZh; }
    private String descEn;
    public String getDescEn() { return descEn; }
    public void setDescEn(String descEn) { this.descEn = descEn; }
    private String videoUrl;
    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
    private String coverImage;
    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    private String duration;
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    private Integer sortOrder;
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    private Integer status;
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
