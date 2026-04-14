package com.ruoyi.brand.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
public class ContactMessage extends BaseEntity {
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    private String email;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    private String company;
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    private String phone;
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    private String subject;
    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }
    private String message;
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    private Integer isRead;
    public Integer getIsRead() { return isRead; }
    public void setIsRead(Integer isRead) { this.isRead = isRead; }
}
