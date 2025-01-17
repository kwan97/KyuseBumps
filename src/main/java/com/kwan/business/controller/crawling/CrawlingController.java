package com.kwan.business.controller.crawling;

import com.kwan.business.core.JsonResponseObject;
import com.kwan.business.param.product.ProductParam;
import com.kwan.business.service.crawling.CrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CrawlingController {

    @Autowired
    private CrawlingService crawlingService;

    /**
     * @title 크롤링
     * @since 2025.01.16
     */
    @PostMapping("/crawling/getCrawlingInfo")
    public JsonResponseObject getCrawlingInfo(JsonResponseObject response, ProductParam param) throws Exception {

        return crawlingService.getCrawlingInfo(response, param);
    }
}
