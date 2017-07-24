package cn.thinkjoy.hsll.controller;

/**
 * Created by wpliu on 16/3/2.
 */

import cn.thinkjoy.hsll.bean.SnsapiUserinfo;
import cn.thinkjoy.hsll.util.StringUtil;
import cn.thinkjoy.hsll.util.WeiXinAuthService;
import cn.thinkjoy.hsll.util.WeiXinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

/**
 * 主页控制类
 */
@Scope("prototype")
@Controller("indexController")
@RequestMapping(value = "/hsll")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    public String auth(HttpServletRequest request){
        String redirect_uri = request.getParameter("redirect_uri");
        if (StringUtil.isBlank(redirect_uri)) {
            return "redirect:/hsll/index"; //跳转到手机站首页
        }

        logger.info("redirect to :" + redirect_uri);

        String INIT_URL = "http://172.16.222.194:8080/hsll/auth/init";
        //微信认证URL
        String WEICHAT_AUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                + WeiXinUtil.getAppid() + "&redirect_uri="
                + URLEncoder.encode(INIT_URL)
                + "&response_type=code&scope=snsapi_userinfo&state="
                + redirect_uri + "#wechat_redirect";
        //跳转到微信认证
        return "redirect:" + WEICHAT_AUTH_URL;
    }


    @RequestMapping(value = "/auth/init",method = RequestMethod.GET)
    public String authInit(HttpServletRequest request){
        String code = request.getParameter("code");
        String redirect_uri = request.getParameter("state");
        if (StringUtil.isBlank(code) || StringUtil.isBlank(redirect_uri)) {
            return "redirect:/hsll/index"; //跳转到手机站首页
        }

        logger.info("weichat code:" + code + "  state:" + redirect_uri);

        SnsapiUserinfo snsapiUserinfo = WeiXinAuthService.getUserinfoFromWeiChrt(code);
        //跳转到微信认证
        return "";
    }



}
