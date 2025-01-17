package com.kwan.business.model.crawling;

import com.kwan.business.core.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrawlingModel extends BaseModel {

    private String title;       /** 제목 */
    private String price;       /** 가격 */
    private String accessUrl;   /** 접속 URL */
    private String imageUrl;    /** 이미지 URL */
    private String info;        /** 정보 */
}
