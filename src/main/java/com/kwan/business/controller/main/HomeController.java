package com.kwan.business.controller.main;

import com.kwan.business.service.main.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private MainService mainServie;

    @RequestMapping("/home")
    public ModelAndView home(ModelAndView mv) {

        return mv;
    }

    @RequestMapping("/tables")
    public ModelAndView tables(ModelAndView mv) {

        return mv;
    }

    @RequestMapping("/charts")
    public ModelAndView charts(ModelAndView mv) {

        return mv;
    }

    @RequestMapping("/productSearch")
    public ModelAndView productSearch(ModelAndView mv) {

        return mv;
    }

    @RequestMapping("/errorPage")
    public ModelAndView errorPage(ModelAndView mv) {

        return mv;
    }

    @RequestMapping("/register")
    public ModelAndView register(ModelAndView mv) {

        return mv;
    }

    @RequestMapping("/cards")
    public ModelAndView componentsCards(ModelAndView mv) {

        return mv;
    }

    @RequestMapping("/buttons")
    public ModelAndView componentsButtons(ModelAndView mv) {

        return mv;
    }

}
