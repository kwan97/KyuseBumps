package com.kwan.business.core;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class JsonResponseObject {

    /** 성공여부 */
    private Boolean success;

    /** 메시지 */
    private String message;

    /** 처리 상세 결과 코드 */
    private String resultCode;

    /** 개발자 정의 맵 */
    private Map<String, Object> resultMap;

    /** Object return */
    public JsonResponseObject addResultMapItem(String key, Object value) {
        if (resultMap == null) {
            this.resultMap = new HashMap<>();
            this.resultMap.put(key, value);
        }
        else {
            this.resultMap.put(key, value);
        }
        return this;
    }
}
