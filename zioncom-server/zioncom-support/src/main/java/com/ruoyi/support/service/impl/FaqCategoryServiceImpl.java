package com.ruoyi.support.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.support.domain.FaqCategory;
import com.ruoyi.support.mapper.FaqCategoryMapper;
import com.ruoyi.support.service.IFaqCategoryService;
@Service
public class FaqCategoryServiceImpl implements IFaqCategoryService {
    @Autowired private FaqCategoryMapper mapper;
    public List<FaqCategory> selectList(FaqCategory param) { return mapper.selectList(param); }
    public FaqCategory selectById(Long id) { return mapper.selectById(id); }
    public int insert(FaqCategory param) { return mapper.insert(param); }
    public int update(FaqCategory param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
