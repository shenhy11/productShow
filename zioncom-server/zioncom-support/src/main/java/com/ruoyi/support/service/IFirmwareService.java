package com.ruoyi.support.service;
import java.util.List;
import com.ruoyi.support.domain.Firmware;
public interface IFirmwareService {
    List<Firmware> selectList(Firmware param);
    Firmware selectById(Long id);
    int insert(Firmware param);
    int update(Firmware param);
    int deleteById(Long id);
}
