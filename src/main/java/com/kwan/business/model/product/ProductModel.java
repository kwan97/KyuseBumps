package com.kwan.business.model.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductModel {

    private String prodNm;
    private String price;
    private String stock;       /**재고*/
    private String imageUrls;
    private String description; /**설명*/
    private String category;    /**카테고리*/
}
