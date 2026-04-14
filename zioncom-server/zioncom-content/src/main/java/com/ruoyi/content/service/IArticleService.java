package com.ruoyi.content.service;
import java.util.List;
import com.ruoyi.content.domain.Article;
public interface IArticleService {
    List<Article> selectList(Article param);
    Article selectById(Long id);
    int insert(Article param);
    int update(Article param);
    int deleteById(Long id);
}
