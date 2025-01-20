package com.kwan.business.service.crawling;

import com.kwan.business.core.JsonResponseObject;
import com.kwan.business.model.crawling.CrawlingExchageModel;
import com.kwan.business.model.crawling.CrawlingProductModel;
import com.kwan.business.param.product.ProductParam;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlingService {

    public ModelAndView getTaxInfo(ModelAndView mv) {

        // 크롬 드라이버 설정
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

        // 크롬 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=500,500");

        //크롤링 할 웹사이트 url
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://help.sell.smartstore.naver.com/faq/content.help?faqId=3558");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        List<WebElement> tabelCode = driver.findElements( By.cssSelector("#content > div.cus_wrap > div.help_content_box > ul > li.contentList.on > div > div:nth-child(18) > table:nth-child(1) > tbody > tr") );
        for (int i = 0; i < tabelCode.size(); i++) {
            List<WebElement> tabelCode2 = driver.findElements( By.cssSelector(tabelCode.get(i) +"> td > p") );

            for (int j = 0; j < tabelCode2.size(); j++) {

            }
        }


//        mv.addObject("taxList", taxList);

        return mv;
    }

    public ModelAndView getExchagetInfo(ModelAndView mv) {
        // 크롬 드라이버 설정
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

        // 크롬 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=500,500");

        //크롤링 할 웹사이트 url
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=환율");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        //제목 크롤링
        List<WebElement> countryList = driver.findElements( By.cssSelector("#main_pack > section.sc_new.cs_nexchangerate > div.api_subject_bx._exchange_rate_banks_official > div.exchange_bx > div.sct_stock_list > div > ul > li > a > dl > dt > .name") );
        //접속 URL 크롤링
        List<WebElement> exchangeList = driver.findElements( By.cssSelector("#main_pack > section.sc_new.cs_nexchangerate > div.api_subject_bx._exchange_rate_banks_official > div.exchange_bx > div.sct_stock_list > div > ul > li > a > dl > dd > span.spt_con.dw > strong") );
        //가격 크롤링
        List<WebElement> discribeRateList = driver.findElements( By.cssSelector("#main_pack > section.sc_new.cs_nexchangerate > div.api_subject_bx._exchange_rate_banks_official > div.exchange_bx > div.sct_stock_list > div > ul > li > a > dl > dd > span.spt_con.dw > span") );
        //이미지 크롤링
//        List<WebElement> imageUrlList = driver.findElements( By.cssSelector("div .product .product_img .image") );

        List<CrawlingExchageModel> exchangeInfoList = new ArrayList<>();

        for (int i = 0; i < countryList.size(); i++) {
            CrawlingExchageModel model = new CrawlingExchageModel();
            model.setCountry(countryList.get(i).getText());
            model.setExchangeRate(exchangeList.get(i).getText());

            String discribe = "";
            if (discribeRateList.get(i).getText().contains("+")) {
                String[] arr = discribeRateList.get(i).getText().split("\n");
                discribe = arr[0]+" +"+arr[1];
            } else if (discribeRateList.get(i).getText().contains("-")) {
                String[] arr = discribeRateList.get(i).getText().split("\n");
                discribe = arr[0]+" -"+arr[1];
            }
            model.setDiscribeRate(discribe);

            exchangeInfoList.add(model);
        }
        driver.quit();

        mv.addObject("exchangeInfoList", exchangeInfoList);

        return mv;
    }

    public JsonResponseObject getCrawlingKream(JsonResponseObject response, ProductParam param) throws Exception {
        // 크롬 드라이버 설정
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

        // 크롬 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=500,500");

        //크롤링 할 웹사이트 url
        WebDriver driver = new ChromeDriver(options);
        if (param.getSearchText().equals("") || param.getSearchText() == null) {
            driver.get("https://kream.co.kr/search?keyword=베이프&tab=products");
//            driver.get("https://search.shopping.naver.com/ns/search?query=베이프&prevQuery=");
        } else {
            driver.get("https://kream.co.kr/search?keyword=베이프"+param.getSearchText()+"&tab=products");
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        //제목 크롤링
        List<WebElement> titleList = driver.findElements( By.cssSelector("div .product_info_area .title .product_info_product_name .translated_name") );
        //접속 URL 크롤링
        List<WebElement> accessUrlList = driver.findElements( By.cssSelector("div .search_result_item .product_card > a") );
        //가격 크롤링
        List<WebElement> priceList = driver.findElements( By.cssSelector("div .price .amount") );
        //이미지 크롤링
        List<WebElement> imageUrlList = driver.findElements( By.cssSelector("div .product .product_img .image") );

        List<CrawlingProductModel> crawlingModelList = new ArrayList<>();

        for (int i = 0; i < titleList.size(); i++) {
            CrawlingProductModel model = new CrawlingProductModel();
            model.setTitle(titleList.get(i).getText());
            model.setPrice(priceList.get(i).getText());
            model.setImageUrl(imageUrlList.get(i).getAttribute("src"));
            model.setAccessUrl(accessUrlList.get(i).getAttribute("href"));

            crawlingModelList.add(model);
        }
        driver.quit();

        if (crawlingModelList.size() > 0) {
            response.addResultMapItem("crawlingModelList", crawlingModelList);
            response.setMessage("크롤링 성공");
            response.setSuccess(true);
        } else {
            response.setMessage("!!!!!크롤링 실패!!!!!");
            response.setSuccess(false);
        }

        return response;
    }

}
