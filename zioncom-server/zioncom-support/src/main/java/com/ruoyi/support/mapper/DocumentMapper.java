package com.ruoyi.support.mapper;
import java.util.List;
import com.ruoyi.support.domain.Document;
public interface DocumentMapper {
    List<Document> selectList(Document param);
    Document selectById(Long id);
    int insert(Document param);
    int update(Document param);
    int deleteById(Long id);
}
