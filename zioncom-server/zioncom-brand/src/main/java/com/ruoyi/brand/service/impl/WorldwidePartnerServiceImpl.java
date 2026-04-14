package com.ruoyi.brand.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.brand.domain.WorldwidePartner;
import com.ruoyi.brand.mapper.WorldwidePartnerMapper;
import com.ruoyi.brand.service.IWorldwidePartnerService;
@Service
public class WorldwidePartnerServiceImpl implements IWorldwidePartnerService {
    @Autowired private WorldwidePartnerMapper mapper;
    public List<WorldwidePartner> selectList(WorldwidePartner param) { return mapper.selectList(param); }
    public WorldwidePartner selectById(Long id) { return mapper.selectById(id); }
    public int insert(WorldwidePartner param) { return mapper.insert(param); }
    public int update(WorldwidePartner param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
