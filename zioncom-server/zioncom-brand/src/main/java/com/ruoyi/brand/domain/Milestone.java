package com.ruoyi.brand.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
public class Milestone extends BaseEntity {
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    private Integer year;
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
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
    private String imageUrl;
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    private Integer sortOrder;
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    private Integer status;
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
