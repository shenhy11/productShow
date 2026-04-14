package com.ruoyi.content.mapper;
import java.util.List;
import com.ruoyi.content.domain.Article;
public interface ArticleMapper {
    List<Article> selectList(Article param);
    Article selectById(Long id);
    int insert(Article param);
    int update(Article param);
    int deleteById(Long id);
}
