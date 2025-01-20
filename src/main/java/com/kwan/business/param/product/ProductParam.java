package com.kwan.business.param.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductParam {

    private String category;    /**카테고리*/
    private String prodNm;
    private String description; /**설명*/

    private String searchText;  /**검색어*/
}
