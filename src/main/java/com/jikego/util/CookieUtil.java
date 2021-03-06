package com.jikego.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Geekerstar(jikewenku.com)
 * Date: 2018/7/23 10:21
 * Description: cookie工具类，解决集群环境下单点登录问题
 */
@Slf4j
public class CookieUtil {
    /**
     * 这个写在了一级域名下，所以二级域名也适用
     */
    private final static String COOKIE_DOMAIN = ".verynavi.com";
    private final static String COOKIE_NAME = "jikego_login_token";

    /**
     * description: 从客户端读取cookie
     *
     * auther: geekerstar
     * date: 2018/12/28 17:09
     * param: [request]
     * return: java.lang.String
     */
    public static String readLoginToken(HttpServletRequest request) {
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            for (Cookie ck : cks) {
                log.info("read cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
                if (StringUtils.equals(ck.getName(), COOKIE_NAME)) {
                    log.info("return cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
                    return ck.getValue();
                }
            }
        }
        return null;
    }

    /**
     * description: 将cookie写入客户端
     *
     * auther: geekerstar
     * date: 2018/12/28 17:09
     * param: [response, token]
     * return: void
     */
    public static void writeLoginToken(HttpServletResponse response, String token) {
        Cookie ck = new Cookie(COOKIE_NAME, token);
        ck.setDomain(COOKIE_DOMAIN);
        //代表设置在根目录
        ck.setPath("/");
        ck.setHttpOnly(true);
        //单位是秒。
        //如果这个maxage不设置的话，cookie就不会写入硬盘，而是写在内存。只在当前页面有效。
        //如果是-1，代表永久
        ck.setMaxAge(60 * 60 * 24 * 365);
        log.info("write cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
        response.addCookie(ck);
    }

    /**
     * description: 删除cookie
     *
     * auther: geekerstar
     * date: 2018/12/28 17:09
     * param: [request, response]
     * return: void
     */
    public static void delLoginToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            for (Cookie ck : cks) {
                if (StringUtils.equals(ck.getName(), COOKIE_NAME)) {
                    ck.setDomain(COOKIE_DOMAIN);
                    ck.setPath("/");
                    //设置成0，代表删除此cookie。
                    ck.setMaxAge(0);
                    log.info("del cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
                    response.addCookie(ck);
                    return;
                }
            }
        }
    }
}
