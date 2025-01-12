package com.kwan.business.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView login(ModelAndView mv) {

        return mv;
    }

    @RequestMapping("/forgot-password")
    public ModelAndView findPwd(ModelAndView mv) {

        return mv;
    }
}
