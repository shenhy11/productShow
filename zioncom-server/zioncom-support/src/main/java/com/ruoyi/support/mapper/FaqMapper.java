package com.ruoyi.support.mapper;
import java.util.List;
import com.ruoyi.support.domain.Faq;
public interface FaqMapper {
    List<Faq> selectList(Faq param);
    Faq selectById(Long id);
    int insert(Faq param);
    int update(Faq param);
    int deleteById(Long id);
}
