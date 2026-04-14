package com.ruoyi.product.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.product.domain.ProductSpec;
import com.ruoyi.product.mapper.ProductSpecMapper;
import com.ruoyi.product.service.IProductSpecService;
@Service
public class ProductSpecServiceImpl implements IProductSpecService {
    @Autowired private ProductSpecMapper mapper;
    public List<ProductSpec> selectList(ProductSpec param) { return mapper.selectList(param); }
    public ProductSpec selectById(Long id) { return mapper.selectById(id); }
    public int insert(ProductSpec param) { return mapper.insert(param); }
    public int update(ProductSpec param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
