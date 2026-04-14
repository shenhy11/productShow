package com.ruoyi.oss.mapper;

import com.ruoyi.oss.domain.OssFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * OSS 文件记录 Mapper 接口
 */
@Mapper
public interface OssFileMapper {

    /**
     * 查询文件列表（支持按分类筛选）
     *
     * @param category 文件分类（可为 null 表示查全部）
     * @return 文件列表
     */
    List<OssFile> selectOssFileList(@Param("category") String category);

    /**
     * 根据 ID 查询文件信息
     *
     * @param id 文件ID
     * @return 文件信息
     */
    OssFile selectOssFileById(@Param("id") Long id);

    /**
     * 根据 OSS ObjectKey 查询文件信息
     *
     * @param objectKey OSS 对象Key
     * @return 文件信息
     */
    OssFile selectOssFileByObjectKey(@Param("objectKey") String objectKey);

    /**
     * 新增文件记录
     *
     * @param ossFile 文件信息
     * @return 影响行数
     */
    int insertOssFile(OssFile ossFile);

    /**
     * 删除文件记录
     *
     * @param id 文件ID
     * @return 影响行数
     */
    int deleteOssFileById(@Param("id") Long id);
}
