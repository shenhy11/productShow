package com.ruoyi.brand.service;
import java.util.List;
import com.ruoyi.brand.domain.Milestone;
public interface IMilestoneService {
    List<Milestone> selectList(Milestone param);
    Milestone selectById(Long id);
    int insert(Milestone param);
    int update(Milestone param);
    int deleteById(Long id);
}
