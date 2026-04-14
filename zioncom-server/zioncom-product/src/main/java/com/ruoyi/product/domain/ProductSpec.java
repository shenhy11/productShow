package com.ruoyi.product.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
public class ProductSpec extends BaseEntity {
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    private Long productId;
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    private String specGroup;
    public String getSpecGroup() { return specGroup; }
    public void setSpecGroup(String specGroup) { this.specGroup = specGroup; }
    private String specKey;
    public String getSpecKey() { return specKey; }
    public void setSpecKey(String specKey) { this.specKey = specKey; }
    private String specValue;
    public String getSpecValue() { return specValue; }
    public void setSpecValue(String specValue) { this.specValue = specValue; }
    private Integer sortOrder;
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
}
