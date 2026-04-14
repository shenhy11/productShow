package com.ruoyi.brand.mapper;
import java.util.List;
import com.ruoyi.brand.domain.ContactMessage;
public interface ContactMessageMapper {
    List<ContactMessage> selectList(ContactMessage param);
    ContactMessage selectById(Long id);
    int insert(ContactMessage param);
    int update(ContactMessage param);
    int deleteById(Long id);
}
