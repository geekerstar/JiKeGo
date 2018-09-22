package com.jikego.service;

import com.github.pagehelper.PageInfo;
import com.jikego.common.ServerResponse;
import com.jikego.pojo.Shipping;

/**
 * @Author: Geekerstar(jikewenku.com)
 * @Date: 2018/6/24 10:47
 * @Description:
 */
public interface IShippingService {
    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse<String> del(Integer userId, Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);

}
