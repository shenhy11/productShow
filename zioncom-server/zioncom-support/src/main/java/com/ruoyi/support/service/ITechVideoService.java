package com.ruoyi.support.service;
import java.util.List;
import com.ruoyi.support.domain.TechVideo;
public interface ITechVideoService {
    List<TechVideo> selectList(TechVideo param);
    TechVideo selectById(Long id);
    int insert(TechVideo param);
    int update(TechVideo param);
    int deleteById(Long id);
}
