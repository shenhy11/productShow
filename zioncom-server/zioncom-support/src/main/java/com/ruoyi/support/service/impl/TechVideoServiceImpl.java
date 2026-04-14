package com.ruoyi.support.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.support.domain.TechVideo;
import com.ruoyi.support.mapper.TechVideoMapper;
import com.ruoyi.support.service.ITechVideoService;
@Service
public class TechVideoServiceImpl implements ITechVideoService {
    @Autowired private TechVideoMapper mapper;
    public List<TechVideo> selectList(TechVideo param) { return mapper.selectList(param); }
    public TechVideo selectById(Long id) { return mapper.selectById(id); }
    public int insert(TechVideo param) { return mapper.insert(param); }
    public int update(TechVideo param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
