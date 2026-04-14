package com.ruoyi.brand.mapper;
import java.util.List;
import com.ruoyi.brand.domain.Milestone;
public interface MilestoneMapper {
    List<Milestone> selectList(Milestone param);
    Milestone selectById(Long id);
    int insert(Milestone param);
    int update(Milestone param);
    int deleteById(Long id);
}
