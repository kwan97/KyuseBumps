package com.kwan.business.service.file;

import com.kwan.business.core.JsonResponseObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
@Service
public class FileService {

    public JsonResponseObject readFile(JsonResponseObject response, MultipartFile file) throws Exception {

        InputStream inputStream = file.getInputStream();// 파일 읽기
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream); // 엑셀 파일 파싱
        XSSFSheet sheet = workbook.getSheetAt(0); // 엑셀 파일의 첫번째 (0) 시트지

        HashMap<Integer, List<String>> fileModelList = new HashMap<>();
        List<String> fileValues = new ArrayList<>();
        Long price = 0L;
        int totalNum = 0;

        // 행의 수
        int rows = sheet.getPhysicalNumberOfRows();

        for (int r = 1; r < rows; r++) { // 마지막에 건수 있음. 첫번째 줄(헤드) 제외
            XSSFRow row = sheet.getRow(r); // 0 ~ rows

            if (row != null) {
                int cells=row.getPhysicalNumberOfCells();// 열의 수
                fileValues = new ArrayList<>(); //초기화

                for (int c = 0; c < cells; c++) {
                    XSSFCell cell = row.getCell(c); // 0 ~ cell
                    String value = "";//초기화

                    // 12반쩨 열이 강의시간(강의실)
                    // r열 c행의 cell이 비어있을 때 continue
                    if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
                        response.setMessage("공백의 셀값이 존재합니다.");
                        response.setSuccess(false);
                        return response;
                    } else {
                        // 타입별로 내용 읽기
                        String resultValue = conversionExcelValue(cell, value);

                        if (resultValue.equals("Error")) {
                            response.setMessage("올바르지 않은 셀값이 존재합니다.");
                            response.setSuccess(false);
                            return response;
                        } else {
                            fileValues.add(resultValue);
                            fileModelList.put(r, fileValues);

                            if (c == 4) {
                                if (resultValue.matches("[+-]?\\d*(\\.\\d+)?")) {
                                    totalNum += Integer.valueOf(resultValue);
                                } else {
                                    response.setMessage("올바르지 않은 셀값이 존재합니다.");
                                    response.setSuccess(false);
                                    return response;
                                }
                            } else if (c == 5) {
                                if (resultValue.replaceAll("[,]", "").matches("[+-]?\\d*(\\.\\d+)?")) {
                                    price += Long.valueOf(resultValue.replaceAll("[.,]", ""));
                                } else {
                                    response.setMessage("올바르지 않은 셀값이 존재합니다.");
                                    response.setSuccess(false);
                                    return response;
                                }
                            }
                        }

                    }
                }
                response.addResultMapItem("totalNum", totalNum);
                response.addResultMapItem("price", NumberFormat.getInstance().format(price));
                response.addResultMapItem("fileModelList", fileModelList);
                response.setSuccess(true);

                inputStream.close();
            }
        }

        return response;
    }

    /**
     * @title 엑셀의 Cell값 String 변환 메서드
     * @since 2025.01.15
     */
    public String conversionExcelValue(XSSFCell cell, String value) {
        switch (cell.getCellType()) {
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case NUMERIC:
                if(DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    value = dateFormat.format(cell.getDateCellValue());
                } else {
                    double doubleVal = cell.getNumericCellValue() ;

                    // 정수/실수 구분 로직
                    if (doubleVal == Math.floor(doubleVal)) {
                        int intVal = (int) cell.getNumericCellValue();

                        //NumberFormat.getInstance().format() => 천단위마다 콤마
                        value = String.valueOf( NumberFormat.getInstance().format(intVal) );
                    } else {
                        value = String.valueOf( NumberFormat.getInstance().format(doubleVal) );
                    }
                }
                break;
            case STRING:
                value = cell.getStringCellValue() + "";
                break;
            case BLANK: // 빈칸에 false 값 들어있는 듯
                value = cell.getBooleanCellValue() + "";
                break;
            case ERROR:
                value = "Error";
                log.error("Error Log: {}", cell.getErrorCellValue());
                break;
            default:
                value = "Error";
                break;
        }
        return value;
    }
}