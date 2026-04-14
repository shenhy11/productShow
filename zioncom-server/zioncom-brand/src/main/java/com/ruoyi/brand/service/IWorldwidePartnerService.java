package com.ruoyi.brand.service;
import java.util.List;
import com.ruoyi.brand.domain.WorldwidePartner;
public interface IWorldwidePartnerService {
    List<WorldwidePartner> selectList(WorldwidePartner param);
    WorldwidePartner selectById(Long id);
    int insert(WorldwidePartner param);
    int update(WorldwidePartner param);
    int deleteById(Long id);
}
