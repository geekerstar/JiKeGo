package com.jikego.service;

import com.jikego.common.ServerResponse;
import com.jikego.vo.CartVo;

/**
 * @Author: Geekerstar(jikewenku.com)
 * @Date: 2018/6/24 8:48
 * @Description:
 */
public interface ICartService {

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId, String productIds);

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> selectOrUnSelect(Integer userId, Integer productId, Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
