package com.ruoyi.support.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.support.domain.Firmware;
import com.ruoyi.support.mapper.FirmwareMapper;
import com.ruoyi.support.service.IFirmwareService;
@Service
public class FirmwareServiceImpl implements IFirmwareService {
    @Autowired private FirmwareMapper mapper;
    public List<Firmware> selectList(Firmware param) { return mapper.selectList(param); }
    public Firmware selectById(Long id) { return mapper.selectById(id); }
    public int insert(Firmware param) { return mapper.insert(param); }
    public int update(Firmware param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
