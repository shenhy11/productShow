package com.ruoyi.product.service;
import java.util.List;
import com.ruoyi.product.domain.ProductSpec;
public interface IProductSpecService {
    List<ProductSpec> selectList(ProductSpec param);
    ProductSpec selectById(Long id);
    int insert(ProductSpec param);
    int update(ProductSpec param);
    int deleteById(Long id);
}
