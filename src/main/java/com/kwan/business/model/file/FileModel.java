package com.kwan.business.model.file;

import com.kwan.business.core.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FileModel extends BaseModel {

    private int brdNo;
    private String brdNm;
    private Date regDts;

    private String cellNo;
    private String cellNm;

    private String rowNo;
    private String rowNm;

    private String columnValue;

}
