package com.ruoyi.support.mapper;
import java.util.List;
import com.ruoyi.support.domain.Firmware;
public interface FirmwareMapper {
    List<Firmware> selectList(Firmware param);
    Firmware selectById(Long id);
    int insert(Firmware param);
    int update(Firmware param);
    int deleteById(Long id);
}
