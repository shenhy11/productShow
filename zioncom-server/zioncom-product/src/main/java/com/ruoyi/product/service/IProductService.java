package com.ruoyi.product.service;
import java.util.List;
import com.ruoyi.product.domain.Product;
public interface IProductService {
    List<Product> selectList(Product param);
    Product selectById(Long id);
    int insert(Product param);
    int update(Product param);
    int deleteById(Long id);
}
