package com.ruoyi.product.service;

import com.ruoyi.product.domain.ProductCategory;
import com.ruoyi.product.mapper.ProductCategoryMapper;
import com.ruoyi.product.service.impl.ProductCategoryServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 白盒测试 - ProductCategoryService 单元测试
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("产品分类 Service 白盒单元测试")
class ProductCategoryServiceTest {

    @Mock
    private ProductCategoryMapper categoryMapper;

    @InjectMocks
    private ProductCategoryServiceImpl categoryService;

    private ProductCategory rootCategory;
    private ProductCategory childCategory;

    @BeforeEach
    void setUp() {
        rootCategory = new ProductCategory();
        rootCategory.setId(1L);
        rootCategory.setNameZh("无线网络");
        rootCategory.setNameEn("Wireless Network");
        rootCategory.setParentId(0L);
        rootCategory.setSortOrder(1);

        childCategory = new ProductCategory();
        childCategory.setId(2L);
        childCategory.setNameZh("无线路由器");
        childCategory.setNameEn("Wireless Router");
        childCategory.setParentId(1L);
        childCategory.setSortOrder(1);
    }

    // ============================================================
    // TC-WB-C001: 查询分类列表
    // ============================================================
    @Test
    @DisplayName("TC-WB-C001: 查询所有分类列表 - 应返回所有节点")
    void selectList_shouldReturnAllCategories() {
        when(categoryMapper.selectList(any())).thenReturn(Arrays.asList(rootCategory, childCategory));

        List<ProductCategory> result = categoryService.selectList(new ProductCategory());

        assertEquals(2, result.size(), "应返回 2 个分类节点");
    }

    // ============================================================
    // TC-WB-C002: 按父节点筛选
    // ============================================================
    @Test
    @DisplayName("TC-WB-C002: 按父 ID 查子分类 - 只应返回直属子节点")
    void selectList_withParentIdFilter_shouldReturnChildren() {
        ProductCategory param = new ProductCategory();
        param.setParentId(1L);
        when(categoryMapper.selectList(param)).thenReturn(List.of(childCategory));

        List<ProductCategory> result = categoryService.selectList(param);

        assertFalse(result.isEmpty());
        result.forEach(c -> assertEquals(1L, c.getParentId(), "子节点父 ID 应为 1"));
    }

    // ============================================================
    // TC-WB-C003: 查询根节点
    // ============================================================
    @Test
    @DisplayName("TC-WB-C003: 按 ID 查根节点 - parentId 应为 0")
    void selectById_rootNode_shouldHaveParentIdZero() {
        when(categoryMapper.selectById(1L)).thenReturn(rootCategory);

        ProductCategory result = categoryService.selectById(1L);

        assertNotNull(result);
        assertEquals(0L, result.getParentId(), "根节点的 parentId 应为 0");
    }

    // ============================================================
    // TC-WB-C004: 删除分类
    // ============================================================
    @Test
    @DisplayName("TC-WB-C004: 删除分类 - 应调用 Mapper 的 deleteById 方法")
    void deleteById_shouldDelegateToMapper() {
        when(categoryMapper.deleteById(2L)).thenReturn(1);

        int result = categoryService.deleteById(2L);

        assertEquals(1, result);
        verify(categoryMapper, only()).deleteById(2L);
    }

    // ============================================================
    // TC-WB-C005: 新增分类双语字段验证
    // ============================================================
    @Test
    @DisplayName("TC-WB-C005: 新增分类时双语字段应完整传入 Mapper")
    void insert_shouldPreserveBilingualFields() {
        when(categoryMapper.insert(any())).thenReturn(1);

        categoryService.insert(childCategory);

        ArgumentCaptor<ProductCategory> captor = ArgumentCaptor.forClass(ProductCategory.class);
        verify(categoryMapper).insert(captor.capture());
        assertNotNull(captor.getValue().getNameZh(), "中文名不应为 null");
        assertNotNull(captor.getValue().getNameEn(), "英文名不应为 null");
        assertEquals("无线路由器", captor.getValue().getNameZh());
        assertEquals("Wireless Router", captor.getValue().getNameEn());
    }
}
