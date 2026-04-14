package com.ruoyi.brand.service;
import java.util.List;
import com.ruoyi.brand.domain.ContactMessage;
public interface IContactMessageService {
    List<ContactMessage> selectList(ContactMessage param);
    ContactMessage selectById(Long id);
    int insert(ContactMessage param);
    int update(ContactMessage param);
    int deleteById(Long id);
}
