package com.kwan.business.param.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductParam {

    private String searchSite;    /**사이트종류*/
    private String prodNm;
    private String description; /**설명*/

    private String searchText;  /**검색어*/
}
