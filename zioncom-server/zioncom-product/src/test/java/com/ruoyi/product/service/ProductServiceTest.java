package com.ruoyi.product.service;

import com.ruoyi.product.domain.Product;
import com.ruoyi.product.mapper.ProductMapper;
import com.ruoyi.product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 白盒测试 - ProductService 单元测试
 * 使用 Mockito 隔离 Mapper 依赖，只测试 Service 业务逻辑
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("产品管理 Service 白盒单元测试")
class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product mockProduct;

    @BeforeEach
    void setUp() {
        // 准备测试用的 Product 对象
        mockProduct = new Product();
        mockProduct.setId(1L);
        mockProduct.setModel("EAP225");
        mockProduct.setNameZh("无线接入点");
        mockProduct.setNameEn("Wireless Access Point");
        mockProduct.setStatus("1"); // 1=已上架
        mockProduct.setCategoryId(100L);
        mockProduct.setSortOrder(1);
    }

    // ============================================================
    // TC-WB-001: selectList - 正常查询列表
    // ============================================================
    @Test
    @DisplayName("TC-WB-001: 正常查询产品列表 - 应返回 Mapper 数据")
    void selectList_shouldReturnProductsFromMapper() {
        // Arrange: Mapper 返回两条数据
        List<Product> mockList = Arrays.asList(mockProduct, new Product());
        when(productMapper.selectList(any(Product.class))).thenReturn(mockList);

        // Act
        Product param = new Product();
        List<Product> result = productService.selectList(param);

        // Assert
        assertNotNull(result, "结果不应为 null");
        assertEquals(2, result.size(), "应返回 2 条产品数据");
        verify(productMapper, times(1)).selectList(param);
    }

    // ============================================================
    // TC-WB-002: selectList - 空结果集
    // ============================================================
    @Test
    @DisplayName("TC-WB-002: 查询产品列表 - 无数据时应返回空列表")
    void selectList_shouldReturnEmptyListWhenNoData() {
        when(productMapper.selectList(any(Product.class))).thenReturn(List.of());

        List<Product> result = productService.selectList(new Product());

        assertNotNull(result, "结果不应为 null");
        assertTrue(result.isEmpty(), "无数据时应返回空列表");
    }

    // ============================================================
    // TC-WB-003: selectById - 正常查询详情
    // ============================================================
    @Test
    @DisplayName("TC-WB-003: 按 ID 查询产品详情 - 应返回对应产品")
    void selectById_shouldReturnProduct() {
        when(productMapper.selectById(1L)).thenReturn(mockProduct);

        Product result = productService.selectById(1L);

        assertNotNull(result, "查询结果不应为 null");
        assertEquals(1L, result.getId(), "ID 应匹配");
        assertEquals("EAP225", result.getModel(), "型号应为 EAP225");
        assertEquals("1", result.getStatus(), "状态应为上架");
        verify(productMapper).selectById(1L);
    }

    // ============================================================
    // TC-WB-004: selectById - 查询不存在的 ID
    // ============================================================
    @Test
    @DisplayName("TC-WB-004: 按不存在的 ID 查询 - 应返回 null")
    void selectById_shouldReturnNullForNonexistentId() {
        when(productMapper.selectById(999L)).thenReturn(null);

        Product result = productService.selectById(999L);

        assertNull(result, "不存在的 ID 应返回 null");
    }

    // ============================================================
    // TC-WB-005: insert - 新增产品成功
    // ============================================================
    @Test
    @DisplayName("TC-WB-005: 新增产品 - 成功时应返回影响行数 1")
    void insert_shouldReturnOneOnSuccess() {
        when(productMapper.insert(any(Product.class))).thenReturn(1);

        int affected = productService.insert(mockProduct);

        assertEquals(1, affected, "新增成功应返回 1");
        verify(productMapper, times(1)).insert(mockProduct);
    }

    // ============================================================
    // TC-WB-006: insert - 新增失败（Mapper 返回0）
    // ============================================================
    @Test
    @DisplayName("TC-WB-006: 新增产品失败 - Mapper 返回 0 时 Service 也应返回 0")
    void insert_shouldReturnZeroOnFailure() {
        when(productMapper.insert(any(Product.class))).thenReturn(0);

        int affected = productService.insert(mockProduct);

        assertEquals(0, affected, "新增失败应返回 0");
    }

    // ============================================================
    // TC-WB-007: update - 更新产品成功
    // ============================================================
    @Test
    @DisplayName("TC-WB-007: 更新产品 - 成功时应调用 Mapper 且返回 1")
    void update_shouldCallMapperAndReturnOne() {
        mockProduct.setNameEn("Updated WAP Name");
        when(productMapper.update(mockProduct)).thenReturn(1);

        int affected = productService.update(mockProduct);

        assertEquals(1, affected, "更新成功应返回 1");
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productMapper).update(captor.capture());
        assertEquals("Updated WAP Name", captor.getValue().getNameEn(), "传入的更新对象英文名应已变更");
    }

    // ============================================================
    // TC-WB-008: deleteById - 删除产品
    // ============================================================
    @Test
    @DisplayName("TC-WB-008: 删除产品 - 正确传递 ID 给 Mapper 并返回 1")
    void deleteById_shouldPassIdToMapperAndReturnOne() {
        when(productMapper.deleteById(1L)).thenReturn(1);

        int affected = productService.deleteById(1L);

        assertEquals(1, affected, "删除成功应返回 1");
        verify(productMapper, times(1)).deleteById(1L);
        // 确保没有调用其他方法（隔离性验证）
        verifyNoMoreInteractions(productMapper);
    }

    // ============================================================
    // TC-WB-009: 按类目筛选查询
    // ============================================================
    @Test
    @DisplayName("TC-WB-009: 按分类 ID 筛选产品 - Mapper 应以正确参数被调用")
    void selectList_withCategoryFilter_shouldPassParamToMapper() {
        Product filterParam = new Product();
        filterParam.setCategoryId(100L);
        when(productMapper.selectList(filterParam)).thenReturn(List.of(mockProduct));

        List<Product> result = productService.selectList(filterParam);

        assertEquals(1, result.size());
        assertEquals(100L, result.get(0).getCategoryId(), "返回产品的分类 ID 应为 100");
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);
        verify(productMapper).selectList(captor.capture());
        assertEquals(100L, captor.getValue().getCategoryId(), "传入 Mapper 的参数分类 ID 应为 100");
    }

    // ============================================================
    // TC-WB-010: 下架状态产品过滤验证
    // ============================================================
    @Test
    @DisplayName("TC-WB-010: 按状态筛选 - 只查下架产品")
    void selectList_withStatusFilter_shouldOnlyReturnMatchingStatus() {
        Product offShelf = new Product();
        offShelf.setId(2L);
        offShelf.setModel("OLD-ROUTER");
        offShelf.setStatus("0"); // 0=已下架

        Product filterParam = new Product();
        filterParam.setStatus("0");
        when(productMapper.selectList(filterParam)).thenReturn(List.of(offShelf));

        List<Product> result = productService.selectList(filterParam);

        assertFalse(result.isEmpty());
        result.forEach(p -> assertEquals("0", p.getStatus(), "所有返回产品状态应为下架"));
    }
}
