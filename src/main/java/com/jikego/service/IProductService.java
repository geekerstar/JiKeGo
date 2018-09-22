package com.jikego.service;

import com.github.pagehelper.PageInfo;
import com.jikego.common.ServerResponse;
import com.jikego.pojo.Product;
import com.jikego.vo.ProductDetailVo;

/**
 * @Author: Geekerstar(jikewenku.com)
 * @Date: 2018/6/23 14:19
 * @Description:
 */
public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);

    ServerResponse<String> setSaleStatus(Integer productId, Integer status);

    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);

    ServerResponse<PageInfo> searchProduct(String productName, Integer productId, int pageNum, int pageSize);

    ServerResponse<ProductDetailVo> getProductDetail(Integer productId);

    ServerResponse<PageInfo> getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);
}
