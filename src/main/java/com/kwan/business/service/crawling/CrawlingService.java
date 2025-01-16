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

    public List<WebElement> getCrawlingInfo(JsonResponseObject response) throws Exception {
        // 크롬 드라이버 설정
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

        // 크롬 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        //크롤링 할 웹사이트 url
        driver.get("https://kream.co.kr/search?keyword=베이프%20의류&tab=products");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        //크롤링 할 리스트 긁어오기
        List<WebElement> elementList = driver.findElements( By.cssSelector("div .search_result_list .search_result_item .product") );
        List<WebElement> finalElementList = new ArrayList<>();
        List<WebElement> elementList2 = new ArrayList<>();
//                driver.findElements( By.cssSelector("div .search_result_list .search_result_item .product") );
        for (int i = 0; i < elementList.size(); i++) {
            elementList2 = driver.findElements( By.cssSelector("div .search_result_list > search_result_item[data-result-index[data-result-index="+i+"]]") );
        }

//        if(elementList.size() > 0) {
//            response.addResultMapItem("kream", elementList.get(0));
//            response.setSuccess(true);
//            response.setMessage("크롤링 성공");
//        }

        driver.quit();

        return elementList2;
    }
}
