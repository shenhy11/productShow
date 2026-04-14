package com.ruoyi.support.mapper;
import java.util.List;
import com.ruoyi.support.domain.TechVideo;
public interface TechVideoMapper {
    List<TechVideo> selectList(TechVideo param);
    TechVideo selectById(Long id);
    int insert(TechVideo param);
    int update(TechVideo param);
    int deleteById(Long id);
}
