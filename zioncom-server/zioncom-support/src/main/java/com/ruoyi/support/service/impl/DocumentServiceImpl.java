package com.ruoyi.support.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.support.domain.Document;
import com.ruoyi.support.mapper.DocumentMapper;
import com.ruoyi.support.service.IDocumentService;
@Service
public class DocumentServiceImpl implements IDocumentService {
    @Autowired private DocumentMapper mapper;
    public List<Document> selectList(Document param) { return mapper.selectList(param); }
    public Document selectById(Long id) { return mapper.selectById(id); }
    public int insert(Document param) { return mapper.insert(param); }
    public int update(Document param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
