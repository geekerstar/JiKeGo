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
 * @author Geekerstar(jikewenku.com)
 * Date: 2018/6/24 10:47
 * Description:
 */

@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {
    @Autowired
    private ShippingMapper shippingMapper;

    /**
     * description: 增加地址
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:40
     * param: [userId, shipping]
     * return: com.jikego.common.ServerResponse
     */
    @Override
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

    /**
     * description: 删除地址
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:40
     * param: [userId, shippingId]
     * return: com.jikego.common.ServerResponse<java.lang.String>
     */
    @Override
    public ServerResponse<String> del(Integer userId, Integer shippingId) {
        int resultCount = shippingMapper.deleteByShippingIdUserId(userId, shippingId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccess("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }

    /**
     * description: 更新地址
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:40
     * param: [userId, shipping]
     * return: com.jikego.common.ServerResponse
     */
    @Override
    public ServerResponse update(Integer userId, Shipping shipping) {
        shipping.setUserId(userId);
        int rowCount = shippingMapper.updateByShipping(shipping);
        if (rowCount > 0) {
            return ServerResponse.createBySuccess("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    /**
     * description: 查询地址
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:41
     * param: [userId, shippingId]
     * return: com.jikego.common.ServerResponse<com.jikego.pojo.Shipping>
     */
    @Override
    public ServerResponse<Shipping> select(Integer userId, Integer shippingId) {
        Shipping shipping = shippingMapper.selectByShippingIdUserId(userId, shippingId);
        if (shipping == null) {
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        return ServerResponse.createBySuccess("更新地址成功", shipping);
    }


    /**
     * description: 分页接口
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:41
     * param: [userId, pageNum, pageSize]
     * return: com.jikego.common.ServerResponse<com.github.pagehelper.PageInfo>
     */
    @Override
    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);
    }

}
