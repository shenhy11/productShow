package com.ruoyi.support.service;

import com.ruoyi.support.domain.Faq;
import com.ruoyi.support.mapper.FaqMapper;
import com.ruoyi.support.service.impl.FaqServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 白盒测试 - FaqService 单元测试
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("FAQ 管理 Service 白盒单元测试")
class FaqServiceTest {

    @Mock
    private FaqMapper faqMapper;

    @InjectMocks
    private FaqServiceImpl faqService;

    private Faq faq1;
    private Faq faq2;

    @BeforeEach
    void setUp() {
        faq1 = new Faq();
        faq1.setId(1L);
        faq1.setCategoryId(10L);
        faq1.setQuestionZh("如何重置我的路由器？");
        faq1.setQuestionEn("How to reset my router?");
        faq1.setAnswerZh("长按机身背部的 Reset 按钮 10 秒钟。");
        faq1.setAnswerEn("Press and hold the Reset button on the back for 10 secs.");
        faq1.setSortOrder(1);

        faq2 = new Faq();
        faq2.setId(2L);
        faq2.setCategoryId(10L);
        faq2.setQuestionZh("默认密码是什么？");
        faq2.setQuestionEn("What is the default password?");
        faq2.setAnswerZh("默认密码通常为空，或参考底部贴纸。");
        faq2.setAnswerEn("Usually blank, or refer to the bottom sticker.");
        faq2.setSortOrder(2);
    }

    @Test
    @DisplayName("TC-WB-F001: 正常查询 FAQ 列表")
    void selectList_shouldReturnFaqList() {
        when(faqMapper.selectList(any())).thenReturn(Arrays.asList(faq1, faq2));

        List<Faq> result = faqService.selectList(new Faq());

        assertEquals(2, result.size());
        verify(faqMapper).selectList(any());
    }

    @Test
    @DisplayName("TC-WB-F002: 按分类 ID 查询 FAQ")
    void selectList_byCategoryId() {
        Faq param = new Faq();
        param.setCategoryId(10L);
        when(faqMapper.selectList(param)).thenReturn(Arrays.asList(faq1, faq2));

        List<Faq> result = faqService.selectList(param);
        assertEquals(2, result.size());
        result.forEach(f -> assertEquals(10L, f.getCategoryId()));
    }

    @Test
    @DisplayName("TC-WB-F003: 正常获取 FAQ 详情")
    void selectById_shouldReturnFaq() {
        when(faqMapper.selectById(1L)).thenReturn(faq1);

        Faq result = faqService.selectById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("如何重置我的路由器？", result.getQuestionZh());
    }

    @Test
    @DisplayName("TC-WB-F004: 新增 FAQ，双语问答完整")
    void insert_shouldPreserveBilingualQandA() {
        when(faqMapper.insert(any())).thenReturn(1);

        faqService.insert(faq1);

        ArgumentCaptor<Faq> captor = ArgumentCaptor.forClass(Faq.class);
        verify(faqMapper).insert(captor.capture());
        Faq capturedFaq = captor.getValue();
        
        assertNotNull(capturedFaq.getQuestionZh());
        assertNotNull(capturedFaq.getQuestionEn());
        assertNotNull(capturedFaq.getAnswerZh());
        assertNotNull(capturedFaq.getAnswerEn());
    }

    @Test
    @DisplayName("TC-WB-F005: 删除 FAQ")
    void deleteById_shouldDelegateToMapper() {
        when(faqMapper.deleteById(1L)).thenReturn(1);

        int result = faqService.deleteById(1L);

        assertEquals(1, result);
        verify(faqMapper).deleteById(1L);
    }
}
