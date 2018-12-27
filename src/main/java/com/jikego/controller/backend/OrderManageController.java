package com.jikego.controller.backend;

import com.github.pagehelper.PageInfo;
import com.jikego.common.ResponseCode;
import com.jikego.common.ServerResponse;
import com.jikego.pojo.User;
import com.jikego.service.IOrderService;
import com.jikego.service.IUserService;
import com.jikego.util.CookieUtil;
import com.jikego.util.JsonUtil;
import com.jikego.util.RedisShardedPoolUtil;
import com.jikego.vo.OrderVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * author: Geekerstar(jikewenku.com)
 * Date: 2018/6/25 13:08
 * Description: 后台订单管理
 */
@Controller
@RequestMapping("/manage/order")
public class OrderManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderService iOrderService;


    /**
     * description: 后台订单列表
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 17:54
     * param: [httpServletRequest, pageNum, pageSize]
     * return: com.jikego.common.ServerResponse<com.github.pagehelper.PageInfo>
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(HttpServletRequest httpServletRequest, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
//
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            //填充我们增加产品的业务逻辑
//            return iOrderService.manageList(pageNum, pageSize);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        //全部通过拦截器验证是否登录及权限
        return iOrderService.manageList(pageNum, pageSize);
    }

    /**
     * description: 后台订单详情
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 17:54
     * param: [httpServletRequest, orderNo]
     * return: com.jikego.common.ServerResponse<com.jikego.vo.OrderVo>
     */
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> orderDetail(HttpServletRequest httpServletRequest, Long orderNo) {
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
//
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            //填充我们增加产品的业务逻辑
//            return iOrderService.manageDetail(orderNo);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        //全部通过拦截器验证是否登录及权限
        return iOrderService.manageDetail(orderNo);
    }

    /**
     * description: 后台订单搜索
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 17:54
     * param: [httpServletRequest, orderNo, pageNum, pageSize]
     * return: com.jikego.common.ServerResponse<com.github.pagehelper.PageInfo>
     */
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(HttpServletRequest httpServletRequest, Long orderNo, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
//
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            //填充我们增加产品的业务逻辑
//            return iOrderService.manageSearch(orderNo, pageNum, pageSize);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        //全部通过拦截器验证是否登录及权限
        return iOrderService.manageSearch(orderNo, pageNum, pageSize);
    }

    /**
     * description: 后台发货
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 17:55
     * param: [httpServletRequest, orderNo]
     * return: com.jikego.common.ServerResponse<java.lang.String>
     */
    @RequestMapping("send_goods.do")
    @ResponseBody
    public ServerResponse<String> orderSendGoods(HttpServletRequest httpServletRequest, Long orderNo) {

//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录管理员");
//
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            //填充我们增加产品的业务逻辑
//            return iOrderService.manageSendGoods(orderNo);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作");
//        }
        //全部通过拦截器验证是否登录及权限
        return iOrderService.manageSendGoods(orderNo);
    }

}
