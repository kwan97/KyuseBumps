package com.kwan.business.controller.main;

import com.kwan.business.service.crawling.CrawlingService;
import com.kwan.business.service.main.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private MainService mainServie;
    @Autowired
    private CrawlingService crawlingService;

    @RequestMapping("/home")
    public ModelAndView home(ModelAndView mv) {

        return mv;
    }

    /**
     * @title 환율정보 조회 페이지
     */
    @RequestMapping("/exchangeRate")
    public ModelAndView exchangeRate(ModelAndView mv) {

        return crawlingService.getExchangeInfo(mv);
    }

    /**
     * @title 관세/판매 수수료 조회 페이지
     */
    @RequestMapping("/taxBoard")
    public ModelAndView taxBoard(ModelAndView mv) {

        return crawlingService.getTaxInfo(mv);
    }

    /**
     * @title 사입 상품 파일 업로드 조회 페이지
     */
    @RequestMapping("/tables")
    public ModelAndView tables(ModelAndView mv) {

        return mv;
    }

//    @RequestMapping("/charts")
//    public ModelAndView charts(ModelAndView mv) {
//
//        return mv;
//    }

    /**
     * @title 실시간 상품 조회 페이지
     */
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
