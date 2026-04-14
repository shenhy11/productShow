package com.ruoyi.content.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
public class Article extends BaseEntity {
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    private String type;
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    private String subType;
    public String getSubType() { return subType; }
    public void setSubType(String subType) { this.subType = subType; }
    private String titleZh;
    public String getTitleZh() { return titleZh; }
    public void setTitleZh(String titleZh) { this.titleZh = titleZh; }
    private String titleEn;
    public String getTitleEn() { return titleEn; }
    public void setTitleEn(String titleEn) { this.titleEn = titleEn; }
    private String coverImage;
    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    private String summaryZh;
    public String getSummaryZh() { return summaryZh; }
    public void setSummaryZh(String summaryZh) { this.summaryZh = summaryZh; }
    private String summaryEn;
    public String getSummaryEn() { return summaryEn; }
    public void setSummaryEn(String summaryEn) { this.summaryEn = summaryEn; }
    private String contentZh;
    public String getContentZh() { return contentZh; }
    public void setContentZh(String contentZh) { this.contentZh = contentZh; }
    private String contentEn;
    public String getContentEn() { return contentEn; }
    public void setContentEn(String contentEn) { this.contentEn = contentEn; }
    private Date publishDate;
    public Date getPublishDate() { return publishDate; }
    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }
    private String status;
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    private Integer viewCount;
    public Integer getViewCount() { return viewCount; }
    public void setViewCount(Integer viewCount) { this.viewCount = viewCount; }
    private String seoTitleZh;
    public String getSeoTitleZh() { return seoTitleZh; }
    public void setSeoTitleZh(String seoTitleZh) { this.seoTitleZh = seoTitleZh; }
    private String seoTitleEn;
    public String getSeoTitleEn() { return seoTitleEn; }
    public void setSeoTitleEn(String seoTitleEn) { this.seoTitleEn = seoTitleEn; }
    private String seoDescZh;
    public String getSeoDescZh() { return seoDescZh; }
    public void setSeoDescZh(String seoDescZh) { this.seoDescZh = seoDescZh; }
    private String seoDescEn;
    public String getSeoDescEn() { return seoDescEn; }
    public void setSeoDescEn(String seoDescEn) { this.seoDescEn = seoDescEn; }
    private String author;
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    private Integer sortOrder;
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}
