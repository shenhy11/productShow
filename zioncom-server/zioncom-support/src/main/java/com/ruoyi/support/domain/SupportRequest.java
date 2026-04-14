package com.ruoyi.support.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
public class SupportRequest extends BaseEntity {
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    private String email;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    private String productModel;
    public String getProductModel() { return productModel; }
    public void setProductModel(String productModel) { this.productModel = productModel; }
    private String subject;
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    private String message;
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    private String status;
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    private String remark;
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}
