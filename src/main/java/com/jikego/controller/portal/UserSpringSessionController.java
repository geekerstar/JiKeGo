package com.jikego.controller.portal;

import com.jikego.common.Const;
import com.jikego.common.ResponseCode;
import com.jikego.common.ServerResponse;
import com.jikego.pojo.User;
import com.jikego.service.IUserService;
import com.jikego.util.CookieUtil;
import com.jikego.util.JsonUtil;
import com.jikego.util.RedisShardedPoolUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: Geekerstar(jikewenku.com)
 * Date: 2018/7/23 13:53
 * Description:
 */
@Controller
@RequestMapping("/user/springsession/")
public class UserSpringSessionController {

    @Autowired
    private IUserService iUserService;


    /**
     * description: 用户登录
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:17
     * param: [username, password, session, httpServletResponse]
     * return: com.jikego.common.ServerResponse<com.jikego.pojo.User>
     */
    @RequestMapping(value = "login.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session, HttpServletResponse httpServletResponse) {
        //测试全局异常
//        int i = 0;
//        int j = 666/i;
        ServerResponse<User> response = iUserService.login(username, password);
        if (response.isSuccess()) {

            session.setAttribute(Const.CURRENT_USER, response.getData());
//            CookieUtil.writeLoginToken(httpServletResponse,session.getId());
//            RedisShardedPoolUtil.setEx(session.getId(), JsonUtil.obj2String(response.getData()),Const.RedisCacheExtime.REDIS_SESSION_EXTIME);

        }
        return response;
    }

    /**
     * description: 登出
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:18
     * param: [session, httpServletRequest, httpServletResponse]
     * return: com.jikego.common.ServerResponse<java.lang.String>
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        CookieUtil.delLoginToken(httpServletRequest,httpServletResponse);
//        RedisShardedPoolUtil.del(loginToken);

        session.removeAttribute(Const.CURRENT_USER);

        return ServerResponse.createBySuccess();
    }

    /**
     * description: 获取用户信息
     * <p>
     * auther: geekerstar
     * date: 2018/12/27 18:18
     * param: [session, httpServletRequest]
     * return: com.jikego.common.ServerResponse<com.jikego.pojo.User>
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session, HttpServletRequest httpServletRequest) {

//        String loginToken = CookieUtil.readLoginToken(httpServletRequest);
//        if(StringUtils.isEmpty(loginToken)){
//            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj(userJsonStr,User.class);

        User user = (User) session.getAttribute(Const.CURRENT_USER);

        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
    }
}
