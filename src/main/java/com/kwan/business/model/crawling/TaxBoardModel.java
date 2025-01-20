package com.kwan.business.model.crawling;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaxBoardModel {

    private String id;
    private String val;

    private List<TaxBoardModel> TaxBoardModelList;
}
