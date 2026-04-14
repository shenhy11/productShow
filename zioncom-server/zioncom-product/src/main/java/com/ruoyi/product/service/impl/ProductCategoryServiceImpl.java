package com.ruoyi.product.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.product.domain.ProductCategory;
import com.ruoyi.product.mapper.ProductCategoryMapper;
import com.ruoyi.product.service.IProductCategoryService;
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {
    @Autowired private ProductCategoryMapper mapper;
    public List<ProductCategory> selectList(ProductCategory param) { return mapper.selectList(param); }
    public ProductCategory selectById(Long id) { return mapper.selectById(id); }
    public int insert(ProductCategory param) { return mapper.insert(param); }
    public int update(ProductCategory param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
