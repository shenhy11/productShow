package com.ruoyi.support.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.support.domain.Faq;
import com.ruoyi.support.mapper.FaqMapper;
import com.ruoyi.support.service.IFaqService;
@Service
public class FaqServiceImpl implements IFaqService {
    @Autowired private FaqMapper mapper;
    public List<Faq> selectList(Faq param) { return mapper.selectList(param); }
    public Faq selectById(Long id) { return mapper.selectById(id); }
    public int insert(Faq param) { return mapper.insert(param); }
    public int update(Faq param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
