package com.ruoyi.support.mapper;
import java.util.List;
import com.ruoyi.support.domain.FaqCategory;
public interface FaqCategoryMapper {
    List<FaqCategory> selectList(FaqCategory param);
    FaqCategory selectById(Long id);
    int insert(FaqCategory param);
    int update(FaqCategory param);
    int deleteById(Long id);
}
