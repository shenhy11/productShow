package com.ruoyi.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.oss.config.OssConfig;
import com.ruoyi.oss.domain.OssFile;
import com.ruoyi.oss.mapper.OssFileMapper;
import com.ruoyi.oss.service.IOssService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * OSS 文件服务实现类
 */
@Service
public class OssServiceImpl implements IOssService {

    private static final Logger log = LoggerFactory.getLogger(OssServiceImpl.class);

    @Autowired(required = false)
    private OSS ossClient;

    @Autowired
    private OssConfig ossConfig;

    @Autowired
    private OssFileMapper ossFileMapper;

    @Override
    public OssFile uploadFile(MultipartFile file, String category) {
        if (ossClient == null) {
            throw new ServiceException("系统未配置 OSS，无法上传文件");
        }
        
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalFilename);
            String fileName = UUID.randomUUID().toString().replace("-", "") + "." + extension;
            
            // 构建 OSS 对象路径：分类(前缀)/年月/随机名.后缀
            String prefix = StringUtils.isEmpty(category) ? "other" : category;
            String objectKey = prefix + "/" + DateUtils.datePath() + "/" + fileName;

            InputStream inputStream = file.getInputStream();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());

            // 上传至 OSS
            ossClient.putObject(ossConfig.getBucketName(), objectKey, inputStream, metadata);

            // 获取生成的公网访问链接
            String url = getPublicUrl(objectKey);

            // 保存到数据库
            OssFile ossFile = new OssFile();
            ossFile.setFileName(originalFilename);
            ossFile.setObjectKey(objectKey);
            ossFile.setFileUrl(url);
            ossFile.setFileSize(file.getSize());
            ossFile.setFileType(file.getContentType());
            ossFile.setCategory(prefix);
            try {
                ossFile.setCreateBy(SecurityUtils.getUsername());
            } catch (Exception e) {
                ossFile.setCreateBy("system");
            }
            
            ossFileMapper.insertOssFile(ossFile);
            return ossFile;
        } catch (Exception e) {
            log.error("上传文件到 OSS 失败", e);
            throw new ServiceException("上传文件失败：" + e.getMessage());
        }
    }

    @Override
    public String getDownloadUrl(Long id) {
        OssFile file = ossFileMapper.selectOssFileById(id);
        if (file == null) {
            throw new ServiceException("文件记录不存在");
        }
        if (ossClient == null) {
            return file.getFileUrl();
        }
        // 设置 URL 过期时间为 1 小时后
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(ossConfig.getBucketName(), file.getObjectKey());
        request.setExpiration(expiration);
        URL signedUrl = ossClient.generatePresignedUrl(request);
        return signedUrl.toString();
    }

    @Override
    public int deleteFile(Long id) {
        OssFile file = ossFileMapper.selectOssFileById(id);
        if (file == null) {
            return 0;
        }
        
        // 从 OSS 删除
        if (ossClient != null) {
            try {
                ossClient.deleteObject(ossConfig.getBucketName(), file.getObjectKey());
            } catch (Exception e) {
                log.error("删除 OSS 文件失败：{}", file.getObjectKey(), e);
            }
        }
        
        // 从数据库删除
        return ossFileMapper.deleteOssFileById(id);
    }

    @Override
    public List<OssFile> selectFileList(String category) {
        return ossFileMapper.selectOssFileList(category);
    }

    /**
     * 拼接公开访问的 URL
     * 如果配置了 CDN 域名，则优先使用 CDN 域名，否则拼接原始 endpoint 域名
     */
    private String getPublicUrl(String objectKey) {
        if (StringUtils.isNotEmpty(ossConfig.getCdnDomain())) {
            String domain = ossConfig.getCdnDomain();
            if (!domain.startsWith("http")) {
                domain = "https://" + domain;
            }
            return domain + (domain.endsWith("/") ? "" : "/") + objectKey;
        }
        
        // 提取 endpoint 中的域名部分
        String endpoint = ossConfig.getEndpoint();
        String prefix = endpoint.startsWith("https") ? "https://" : "http://";
        String domain = endpoint.replace(prefix, "");
        
        return prefix + ossConfig.getBucketName() + "." + domain + "/" + objectKey;
    }
}
