package com.ruoyi.support.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.support.domain.SupportRequest;
import com.ruoyi.support.mapper.SupportRequestMapper;
import com.ruoyi.support.service.ISupportRequestService;
@Service
public class SupportRequestServiceImpl implements ISupportRequestService {
    @Autowired private SupportRequestMapper mapper;
    public List<SupportRequest> selectList(SupportRequest param) { return mapper.selectList(param); }
    public SupportRequest selectById(Long id) { return mapper.selectById(id); }
    public int insert(SupportRequest param) { return mapper.insert(param); }
    public int update(SupportRequest param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
