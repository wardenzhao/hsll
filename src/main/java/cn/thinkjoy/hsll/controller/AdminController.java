package cn.thinkjoy.hsll.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wpliu on 17/8/13.
 * 后台管理控制类
 */
@Scope("prototype")
@Controller("adminController")
@RequestMapping(value = "/admin")
public class AdminController {

}
