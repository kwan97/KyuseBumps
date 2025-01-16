package com.kwan.business.model.crawling;

import com.kwan.business.core.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrawlingModel extends BaseModel {

    private String title;
    private String price;
    private String info;
    private String url;
}
