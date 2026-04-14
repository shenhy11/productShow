package com.ruoyi.product.mapper;
import java.util.List;
import com.ruoyi.product.domain.Product;
public interface ProductMapper {
    List<Product> selectList(Product param);
    Product selectById(Long id);
    int insert(Product param);
    int update(Product param);
    int deleteById(Long id);
}
