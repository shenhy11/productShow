package com.ruoyi.product.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.ruoyi.product.domain.Product;
import com.ruoyi.product.mapper.ProductMapper;
import com.ruoyi.product.service.IProductService;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired private ProductMapper mapper;
    public List<Product> selectList(Product param) { return mapper.selectList(param); }
    public Product selectById(Long id) { return mapper.selectById(id); }
    public int insert(Product param) { return mapper.insert(param); }
    public int update(Product param) { return mapper.update(param); }
    public int deleteById(Long id) { return mapper.deleteById(id); }
}
