package com.jikego.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.jikego.common.ServerResponse;
import com.jikego.dao.ShippingMapper;
import com.jikego.pojo.Shipping;
import com.jikego.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Geekerstar(jikewenku.com)
 * @Date: 2018/6/24 10:47
 * @Description:
 */

@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {
    @Autowired
    private ShippingMapper shippingMapper;

    /*
     * @Description: 增加地址
     *
     * @auther: Geekerstar(jikewenku.com)
     * @date: 2018/7/21 21:05
     * @param: [userId, shipping]
     * @return: com.jikego.common.ServerResponse
     */
    public ServerResponse add(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.insert(shipping);
        if (rowCount > 0) {
            Map result = Maps.newHashMap();
            result.put("shippingId", shipping.getId());
            return ServerResponse.createBySuccess("新建地址成功", result);
        }
        return ServerResponse.createByErrorMessage("新建地址失败");
    }

    /*
     * @Description: 删除地址
     *
     * @auther: Geekerstar(jikewenku.com)
     * @date: 2018/7/21 21:07
     * @param: [userId, shippingId]
     * @return: com.jikego.common.ServerResponse<java.lang.String>
     */
    public ServerResponse<String> del(Integer userId, Integer shippingId) {
        int resultCount = shippingMapper.deleteByShippingIdUserId(userId, shippingId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }

    /*
     * @Description: 更新地址
     *
     * @auther: Geekerstar(jikewenku.com)
     * @date: 2018/7/21 21:10
     * @param: [userId, shipping]
     * @return: com.jikego.common.ServerResponse
     */
    public ServerResponse update(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.updateByShipping(shipping);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    /*
     * @Description: 查询地址
     *
     * @auther: Geekerstar(jikewenku.com)
     * @date: 2018/7/21 21:12
     * @param: [userId, shippingId]
     * @return: com.jikego.common.ServerResponse<com.jikego.pojo.Shipping>
     */
    public ServerResponse<Shipping> select(Integer userId, Integer shippingId) {
        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId, shippingId);
        if (shipping == null) {
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        return ServerResponse.createBySuccess("更新地址成功", shipping);
    }


    /*
     * @Description: 分页接口
     *
     * @auther: Geekerstar(jikewenku.com)
     * @date: 2018/7/21 21:14
     * @param: [userId, pageNum, pageSize]
     * @return: com.jikego.common.ServerResponse<com.github.pagehelper.PageInfo>
     */
    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);
    }

}
