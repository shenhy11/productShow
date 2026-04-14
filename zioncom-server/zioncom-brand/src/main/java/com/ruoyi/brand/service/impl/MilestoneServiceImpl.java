package com.ruoyi.brand.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.brand.domain.Milestone;
import com.ruoyi.brand.mapper.MilestoneMapper;
import com.ruoyi.brand.service.IMilestoneService;
@Service
public class MilestoneServiceImpl implements IMilestoneService {
    @Autowired private MilestoneMapper mapper;
    public List<Milestone> selectList(Milestone param) { return mapper.selectList(param); }
    public Milestone selectById(Long id) { return mapper.selectById(id); }
    public int insert(Milestone param) { return mapper.insert(param); }
    public int update(Milestone param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
