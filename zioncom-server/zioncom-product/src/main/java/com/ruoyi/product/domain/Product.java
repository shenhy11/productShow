package com.ruoyi.product.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
public class Product extends BaseEntity {
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    private Long categoryId;
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    private String model;
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    private String nameZh;
    public String getNameZh() { return nameZh; }
    public void setNameZh(String nameZh) { this.nameZh = nameZh; }
    private String nameEn;
    public String getNameEn() { return nameEn; }
    public void setNameEn(String nameEn) { this.nameEn = nameEn; }
    private String summaryZh;
    public String getSummaryZh() { return summaryZh; }
    public void setSummaryZh(String summaryZh) { this.summaryZh = summaryZh; }
    private String summaryEn;
    public String getSummaryEn() { return summaryEn; }
    public void setSummaryEn(String summaryEn) { this.summaryEn = summaryEn; }
    private String detailZh;
    public String getDetailZh() { return detailZh; }
    public void setDetailZh(String detailZh) { this.detailZh = detailZh; }
    private String detailEn;
    public String getDetailEn() { return detailEn; }
    public void setDetailEn(String detailEn) { this.detailEn = detailEn; }
    private String coverImage;
    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    private String bannerImages;
    public String getBannerImages() { return bannerImages; }
    public void setBannerImages(String bannerImages) { this.bannerImages = bannerImages; }
    private String status;
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
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
    private Integer sortOrder;
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}
