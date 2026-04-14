package com.ruoyi.oss.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云 OSS 配置
 * 通过 application.yml 中 zioncom.oss.* 注入
 */
@Configuration
@ConfigurationProperties(prefix = "zioncom.oss")
public class OssConfig {

    /** OSS 访问端点，如 https://oss-cn-shenzhen.aliyuncs.com */
    private String endpoint;

    /** AccessKey ID */
    private String accessKeyId;

    /** AccessKey Secret */
    private String accessKeySecret;

    /** Bucket 名称 */
    private String bucketName;

    /** CDN 加速域名（可选），若配置则使用该域名替代 OSS 域名 */
    private String cdnDomain;

    /**
     * 初始化 OSS 客户端 Bean
     * 若 endpoint 未配置（本地开发），返回 null 跳过初始化
     */
    @Bean
    public OSS ossClient() {
        if (endpoint == null || endpoint.isBlank()) {
            return null;
        }
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    // ===== Getter / Setter =====

    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

    public String getAccessKeyId() { return accessKeyId; }
    public void setAccessKeyId(String accessKeyId) { this.accessKeyId = accessKeyId; }

    public String getAccessKeySecret() { return accessKeySecret; }
    public void setAccessKeySecret(String accessKeySecret) { this.accessKeySecret = accessKeySecret; }

    public String getBucketName() { return bucketName; }
    public void setBucketName(String bucketName) { this.bucketName = bucketName; }

    public String getCdnDomain() { return cdnDomain; }
    public void setCdnDomain(String cdnDomain) { this.cdnDomain = cdnDomain; }
}
