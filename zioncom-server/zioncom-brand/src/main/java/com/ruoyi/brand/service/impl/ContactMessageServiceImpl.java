package com.ruoyi.brand.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.brand.domain.ContactMessage;
import com.ruoyi.brand.mapper.ContactMessageMapper;
import com.ruoyi.brand.service.IContactMessageService;
@Service
public class ContactMessageServiceImpl implements IContactMessageService {
    @Autowired private ContactMessageMapper mapper;
    public List<ContactMessage> selectList(ContactMessage param) { return mapper.selectList(param); }
    public ContactMessage selectById(Long id) { return mapper.selectById(id); }
    public int insert(ContactMessage param) { return mapper.insert(param); }
    public int update(ContactMessage param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
