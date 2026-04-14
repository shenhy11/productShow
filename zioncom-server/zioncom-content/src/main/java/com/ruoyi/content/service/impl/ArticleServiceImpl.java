package com.ruoyi.content.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.content.domain.Article;
import com.ruoyi.content.mapper.ArticleMapper;
import com.ruoyi.content.service.IArticleService;
@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired private ArticleMapper mapper;
    public List<Article> selectList(Article param) { return mapper.selectList(param); }
    public Article selectById(Long id) { return mapper.selectById(id); }
    public int insert(Article param) { return mapper.insert(param); }
    public int update(Article param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
