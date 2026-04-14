package com.ruoyi.support.service;
import java.util.List;
import com.ruoyi.support.domain.SupportRequest;
public interface ISupportRequestService {
    List<SupportRequest> selectList(SupportRequest param);
    SupportRequest selectById(Long id);
    int insert(SupportRequest param);
    int update(SupportRequest param);
    int deleteById(Long id);
}
