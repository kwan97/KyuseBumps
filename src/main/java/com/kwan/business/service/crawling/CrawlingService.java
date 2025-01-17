package com.kwan.business.service.crawling;

import com.kwan.business.core.JsonResponseObject;
import com.kwan.business.model.crawling.CrawlingModel;
import com.kwan.business.param.product.ProductParam;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlingService {

    public JsonResponseObject getCrawlingKream(JsonResponseObject response, ProductParam param) throws Exception {
        // 크롬 드라이버 설정
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

        // 크롬 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=500,500");

        //크롤링 할 웹사이트 url
        WebDriver driver = new ChromeDriver(options);
        if (param.getCategory().equals("베이프")) {
            driver.get("https://kream.co.kr/search?keyword=베이프&tab=products");
//            driver.get("https://search.shopping.naver.com/ns/search?query=베이프&prevQuery=");
        } else {
            driver.get("https://kream.co.kr/search?keyword=베이프"+param.getCategory()+"&tab=products");
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

        List<CrawlingModel> crawlingModelList = new ArrayList<>();

        for (int i = 0; i < titleList.size(); i++) {
            CrawlingModel model = new CrawlingModel();
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
