package com.ruoyi.brand.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
public class WorldwidePartner extends BaseEntity {
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    private String region;
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    private String country;
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    private String website;
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    private String email;
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    private String phone;
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    private String address;
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    private Double longitude;
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    private Double latitude;
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    private String logoUrl;
    public String getLogoUrl() { return logoUrl; }
    public void setLogoUrl(String logoUrl) { this.logoUrl = logoUrl; }
    private Integer sortOrder;
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    private Integer status;
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
