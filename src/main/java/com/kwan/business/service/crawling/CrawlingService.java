package com.kwan.business.service.crawling;

import com.kwan.business.core.JsonResponseObject;
import com.kwan.business.model.crawling.CrawlingExchageModel;
import com.kwan.business.model.crawling.CrawlingProductModel;
import com.kwan.business.param.product.ProductParam;
import io.netty.util.internal.ObjectUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CrawlingService {

    public ModelAndView getExchangeInfo(ModelAndView mv) {
        try{
            // 크롬 드라이버 설정
            System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

            // 크롬 옵션 설정
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            //options.addArguments("--window-size=500, 500");
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");

            //크롤링 할 웹사이트 url
            WebDriver driver = new ChromeDriver(options);
            driver.get("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=환율");
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

            String url = "#main_pack > section.sc_new.cs_nexchangerate > div.api_subject_bx._exchange_rate_banks_official > div.exchange_bx > div.sct_stock_list > div > ul > li > a > dl ";

            //제목 크롤링
            List<WebElement> countryList = driver.findElements( By.cssSelector(url + "> dt > span.name") );
            //접속 URL 크롤링
            List<WebElement> exchangeList = driver.findElements( By.cssSelector(url + "> dd > span.spt_con.dw > strong") );
            //가격 크롤링
            List<WebElement> discribeRateList = driver.findElements( By.cssSelector(url + "> dd > span.spt_con.dw > span.n_ch") );

            List<CrawlingExchageModel> exchangeInfoList = new ArrayList<>();
            for (int i = 0; i < countryList.size(); i++) {
                CrawlingExchageModel model = new CrawlingExchageModel();
                model.setCountry(countryList.get(i).getText());
                model.setExchangeRate(exchangeList.get(i).getText());

                String discribe = "";
                if (discribeRateList.get(i).getText().contains("-")) {
                    String[] arr = discribeRateList.get(i).getText().split("\n");
                    discribe = arr[0]+" -"+arr[1];
                } else {
                    String[] arr = discribeRateList.get(i).getText().split("\n");
                    discribe = arr[0]+" +"+arr[1];
                }
                model.setDiscribeRate(discribe);

                exchangeInfoList.add(model);
            }
            driver.quit();
            mv.addObject("exchangeInfoList", exchangeInfoList);
        } catch (IndexOutOfBoundsException ioobe) {
            ioobe.printStackTrace();
        } finally {
            return mv;
        }
    }

    public ModelAndView getTaxInfo(ModelAndView mv) {

        // 크롬 드라이버 설정
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

        // 크롬 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("--window-size=500,500");
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");

        //크롤링 할 웹사이트 url
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://help.sell.smartstore.naver.com/faq/content.help?faqId=3558");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        String url = "#content > div.cus_wrap > div.help_content_box > ul > li.contentList.on > div > div:nth-child(18) > table:nth-child(1) > tbody";
        List<WebElement> tr = driver.findElements( By.cssSelector(url + "> tr") );
        List<WebElement> td = driver.findElements( By.cssSelector(url + "> tr > td") );

        if (td.size() > 0 && td != null) {
            List<String> tdList = new ArrayList<>();
            String tdInfo;

            for (int i = 0; i < td.size(); i++) {
                tdInfo = td.get(i).getText().replaceAll(" ", "");
                tdList.add(tdInfo);
                tdInfo = "";
            }
            mv.addObject("tdList", tdList);
            mv.addObject("trList", tr);
        }

        return mv;
    }

    public JsonResponseObject getCrawlingProductList(JsonResponseObject response, ProductParam param) throws Exception {
        // 크롬 드라이버 설정
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

        // 크롬 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=500,500");
//        options.addArguments("--headless");
//        options.addArguments("--no-sandbox");

        //크롤링 할 웹사이트 url
        WebDriver driver = new ChromeDriver(options);
        List<WebElement> total = new ArrayList<>();
        List<WebElement> title = new ArrayList<>();
        List<WebElement> accessUrl = new ArrayList<>();
        List<WebElement> imageUrl = new ArrayList<>();
        List<WebElement> price = new ArrayList<>();

        if (param.getSearchText().equals("") || param.getSearchText() == null) {
            response.setMessage("검색할 상품을 입력해주세요.");
            response.setSuccess(false);
            return response;
        } else {
            if (param.getSearchSite().equals("naver")) {
                driver.get("https://search.shopping.naver.com/ns/search?query="+param.getSearchText());

                String url = "#composite-card-list > div > ul.compositeCardList_product_list__Ih4JR > li";

                total = driver.findElements( By.cssSelector(url) );
                accessUrl = driver.findElements( By.cssSelector(url +  "") );
                imageUrl = driver.findElements( By.cssSelector(url +  "") );
                price = driver.findElements( By.cssSelector(url +  "") );
            } else if (param.getSearchSite().equals("coupang")) {

            } else if (param.getSearchSite().equals("taobao")) {

            } else if (param.getSearchSite().equals("lacuten")) {

            }
//            driver.get("https://kream.co.kr/search?keyword=베이프"+param.getSearchText()+"&tab=products");
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

//        //제목 크롤링
//        List<WebElement> titleList = driver.findElements( By.cssSelector("div .product_info_area .title .product_info_product_name .translated_name") );
//        //접속 URL 크롤링
//        List<WebElement> accessUrlList = driver.findElements( By.cssSelector("div .search_result_item .product_card > a") );
//        //가격 크롤링
//        List<WebElement> priceList = driver.findElements( By.cssSelector("div .price .amount") );
//        //이미지 크롤링
//        List<WebElement> imageUrlList = driver.findElements( By.cssSelector("div .product .product_img .image") );

//        List<CrawlingProductModel> crawlingModelList = new ArrayList<>();

//        for (int i = 0; i < titleList.size(); i++) {
//            CrawlingProductModel model = new CrawlingProductModel();
//            model.setTitle(titleList.get(i).getText());
//            model.setPrice(priceList.get(i).getText());
//            model.setImageUrl(imageUrlList.get(i).getAttribute("src"));
//            model.setAccessUrl(accessUrlList.get(i).getAttribute("href"));
//
//            crawlingModelList.add(model);
//        }
        driver.quit();

        if (total.size() > 0) {
            response.addResultMapItem("crawlingModelList", total);
            response.setMessage("크롤링 성공");
            response.setSuccess(true);
        } else {
            response.setMessage("!!!!!크롤링 실패!!!!!");
            response.setSuccess(false);
        }

        return response;
    }

}
