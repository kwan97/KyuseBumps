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

    public JsonResponseObject getCrawlingInfo(JsonResponseObject response, ProductParam param) throws Exception {
        // 크롬 드라이버 설정
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

        // 크롬 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        //크롤링 할 웹사이트 url
        driver.get("https://kream.co.kr/search?keyword=베이프%20의류&tab=products");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        //크롤링 할 리스트 긁어오기
//        List<WebElement> elementList = driver.findElements( By.cssSelector("div .search_result_list .search_result_item .product") );
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
            WebElement webTitle = titleList.get(i);
            WebElement webPrice = priceList.get(i);
            WebElement webImageUrl = imageUrlList.get(i);
            WebElement webAccessUrl = accessUrlList.get(i);

            CrawlingModel model = new CrawlingModel();
            model.setTitle(webTitle.getText());
            model.setPrice(webPrice.getText());
            model.setImageUrl(webImageUrl.getAttribute("src"));
            model.setAccessUrl(webAccessUrl.getAttribute("href"));

            crawlingModelList.add(model);
        }
        response.addResultMapItem("crawlingModelList", crawlingModelList);

        driver.quit();

        return response;
    }
}
