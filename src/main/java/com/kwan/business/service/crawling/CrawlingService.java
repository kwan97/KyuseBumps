package com.kwan.business.service.crawling;

import com.kwan.business.core.JsonResponseObject;
import com.kwan.business.model.crawling.CrawlingModel;
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

    public JsonResponseObject getCrawlingInfo(JsonResponseObject response) throws Exception {
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
        List<WebElement> titleList = driver.findElements( By.cssSelector("div .product_info_area .title .product_info_product_name .translated_name") );
        List<WebElement> priceList = driver.findElements( By.cssSelector("div .price .amount") );

        String title = "";
        String price = "";
        List<CrawlingModel> crawlingModels = new ArrayList<>();
        for (int i = 0; i < titleList.size(); i++) {
            WebElement webTitle = titleList.get(i);
            WebElement webPrice = priceList.get(i);

            CrawlingModel model = new CrawlingModel();
            model.setTitle(webTitle.getText());
            model.setPrice(webPrice.getText());
//            title = webTitle.getText();
//            price = webPrice.getText();
            crawlingModels.add(model);

//            response.addResultMapItem("title"+i, title);
//            response.addResultMapItem("price"+i, price);
        }
        response.addResultMapItem("crawlingModels", crawlingModels);

        driver.quit();

        return response;
    }
}
