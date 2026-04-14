package com.ruoyi.support.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
public class FaqCategory extends BaseEntity {
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    private String nameZh;
    public String getNameZh() { return nameZh; }
    public void setNameZh(String nameZh) { this.nameZh = nameZh; }
    private String nameEn;
    public String getNameEn() { return nameEn; }
    public void setNameEn(String nameEn) { this.nameEn = nameEn; }
    private Integer sortOrder;
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    private Integer status;
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
