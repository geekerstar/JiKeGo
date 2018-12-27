package com.jikego.controller.backend;


import com.jikego.common.ResponseCode;
import com.jikego.common.ServerResponse;
import com.jikego.pojo.User;
import com.jikego.service.ICategoryService;
import com.jikego.service.IUserService;
import com.jikego.util.CookieUtil;
import com.jikego.util.JsonUtil;
import com.jikego.util.RedisShardedPoolUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Geekerstar(jikewenku.com)
 * @Date: 2018/6/23 10:58
 * @Description: 后台分类管理
 */
@Controller
@RequestMapping("/manage/category")
public class CategoryManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;


    @RequestMapping("add_category.do")
    @ResponseBody
    public ServerResponse addCategory(HttpServletRequest httpServletRequest, String categoryName, @RequestParam(value = "parentId", defaultValue = "0") int parentId) {
        /**
         * @description: 增加分类
         *
         * @auther: geekerstar
         * @date: 2018/12/27 15:31
         * @param: [httpServletRequest, categoryName, parentId]
         * @return: com.jikego.common.ServerResponse
         */
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
//        }
//        //校验一下是否是管理员
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            //是管理员
//            //增加我们处理分类的逻辑
//            return iCategoryService.addCategory(categoryName, parentId);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
//        }
        //全部通过拦截器验证是否登录及权限
        return iCategoryService.addCategory(categoryName, parentId);
    }


    @RequestMapping("set_category_name.do")
    @ResponseBody
    public ServerResponse setCategoryName(HttpServletRequest httpServletRequest, Integer categoryId, String categoryName) {
        /**
         * @description: 设置分类名
         *
         * @auther: geekerstar
         * @date: 2018/12/27 15:32
         * @param: [httpServletRequest, categoryId, categoryName]
         * @return: com.jikego.common.ServerResponse
         */
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            //更新CategoryName
//            return iCategoryService.updateCategoryName(categoryId, categoryName);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
//        }
        //全部通过拦截器验证是否登录及权限
        return iCategoryService.updateCategoryName(categoryId, categoryName);
    }


    @RequestMapping("get_category.do")
    @ResponseBody
    public ServerResponse getChildrenParallelCategory(HttpServletRequest httpServletRequest, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        /**
         * @description: 获取平级分类信息
         *
         * @auther: geekerstar
         * @date: 2018/12/27 15:32
         * @param: [httpServletRequest, categoryId]
         * @return: com.jikego.common.ServerResponse
         */
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            //查询子节点的category信息，并且不递归，保持平级
//            return iCategoryService.getChildrenParallelCategory(categoryId);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
//        }
        //全部通过拦截器验证是否登录及权限
        return iCategoryService.getChildrenParallelCategory(categoryId);
    }


    @RequestMapping("get_deep_category.do")
    @ResponseBody
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpServletRequest httpServletRequest, @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {
        /**
         * @description: 获取当前分类id并且递归查询子节点的分类id
         *
         * @auther: geekerstar
         * @date: 2018/12/27 15:33
         * @param: [httpServletRequest, categoryId]
         * @return: com.jikego.common.ServerResponse
         */
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user == null) {
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录,请登录");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()) {
//            //查询当前节点的id和递归子节点的id
//            //0-->10000-->100000
//            return iCategoryService.selectCategoryAndChildrenById(categoryId);
//        } else {
//            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
//        }
        //全部通过拦截器验证是否登录及权限
        return iCategoryService.selectCategoryAndChildrenById(categoryId);
    }


}
