package com.ruoyi.product.service;
import java.util.List;
import com.ruoyi.product.domain.ProductCategory;
public interface IProductCategoryService {
    List<ProductCategory> selectList(ProductCategory param);
    ProductCategory selectById(Long id);
    int insert(ProductCategory param);
    int update(ProductCategory param);
    int deleteById(Long id);
}
