package com.kwan.business.schedule;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class AutoStoreUploadSchedule {

    private static final String IMAGE_UPLOAD_API_URL = "https://api.commerce.naver.com/external/v1/images";
    private static final String PRODUCT_API_URL = "https://api.commerce.naver.com/external/v1/products";

    private static final String CLIENT_ID = "YOUR_CLIENT_ID"; // 네이버 API Client ID
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET"; // 네이버 API Secret
    private static final String ACCESS_TOKEN = "YOUR_ACCESS_TOKEN"; // OAuth 인증 토큰


    @Scheduled(cron = "0 0 * * * 1-5")
    private String registerProductToNaver(String name, String description, int price, int stock, String category, List<String> imageUrls) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(PRODUCT_API_URL);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("X-Naver-Client-Id", CLIENT_ID);
            request.setHeader("X-Naver-Client-Secret", CLIENT_SECRET);
            request.setHeader("Authorization", "Bearer " + ACCESS_TOKEN);

            // 상품 정보 JSON 생성
            JSONObject productJson = new JSONObject();
            productJson.put("name", name);
            productJson.put("description", description);
            productJson.put("price", price);
            productJson.put("stock", stock);
            productJson.put("category", category);
            productJson.put("imageUrls", imageUrls); // 여러 개의 이미지 URL 추가

            StringEntity entity = new StringEntity(productJson.toString());
            request.setEntity(entity);

            // 요청 실행
            try (CloseableHttpResponse response = httpClient.execute(request);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {

                StringBuilder responseBody = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }
                System.out.println("✅ 상품 등록 완료: " + responseBody.toString());
                return responseBody.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "!!!상품 등록 실패!!!";
        }
    }

    private String uploadImage(MultipartFile file) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(IMAGE_UPLOAD_API_URL);
            request.setHeader("X-Naver-Client-Id", CLIENT_ID);
            request.setHeader("X-Naver-Client-Secret", CLIENT_SECRET);
            request.setHeader("Authorization", "Bearer " + ACCESS_TOKEN);

            // Multipart 파일 업로드 설정
            MultipartEntity entity = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.STRICT)
                    .addBinaryBody("file", file.getInputStream(), org.apache.hc.core5.http.ContentType.IMAGE_JPEG, file.getOriginalFilename())
                    .build();

            request.setEntity(entity);

            // API 요청 실행
            try (CloseableHttpResponse response = httpClient.execute(request);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {

                StringBuilder responseBody = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseBody.append(line);
                }

                // 응답 JSON 파싱
                JSONObject jsonResponse = new JSONObject(responseBody.toString());
                return jsonResponse.getString("url"); // 업로드된 이미지 URL 반환
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
