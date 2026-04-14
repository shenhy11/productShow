package com.ruoyi.product.mapper;
import java.util.List;
import com.ruoyi.product.domain.ProductSpec;
public interface ProductSpecMapper {
    List<ProductSpec> selectList(ProductSpec param);
    ProductSpec selectById(Long id);
    int insert(ProductSpec param);
    int update(ProductSpec param);
    int deleteById(Long id);
}
