package com.ruoyi.support.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.support.domain.WebEmulator;
import com.ruoyi.support.mapper.WebEmulatorMapper;
import com.ruoyi.support.service.IWebEmulatorService;
@Service
public class WebEmulatorServiceImpl implements IWebEmulatorService {
    @Autowired private WebEmulatorMapper mapper;
    public List<WebEmulator> selectList(WebEmulator param) { return mapper.selectList(param); }
    public WebEmulator selectById(Long id) { return mapper.selectById(id); }
    public int insert(WebEmulator param) { return mapper.insert(param); }
    public int update(WebEmulator param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
