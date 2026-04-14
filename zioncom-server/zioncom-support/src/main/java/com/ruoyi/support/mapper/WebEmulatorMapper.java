package com.ruoyi.support.mapper;
import java.util.List;
import com.ruoyi.support.domain.WebEmulator;
public interface WebEmulatorMapper {
    List<WebEmulator> selectList(WebEmulator param);
    WebEmulator selectById(Long id);
    int insert(WebEmulator param);
    int update(WebEmulator param);
    int deleteById(Long id);
}
