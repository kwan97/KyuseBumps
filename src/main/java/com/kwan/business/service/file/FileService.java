package com.kwan.business.service.file;

import com.kwan.business.model.file.FileModel;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;



@Service
public class FileService {

    public LinkedHashMap<Integer, List<String>> readFile(MultipartFile file) throws Exception {

        InputStream inputStream = file.getInputStream();// 파일 읽기
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream); // 엑셀 파일 파싱
        XSSFSheet sheet = workbook.getSheetAt(0); // 엑셀 파일의 첫번째 (0) 시트지

        LinkedHashMap<Integer, List<String>> fileModelList = new LinkedHashMap<>();
        List<String> fileValues = new ArrayList<>();

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
                    // r열 c행의 cell이 비어있을 때 혹은 시간표 열이 아닐때
                    if (cell == null || cell.getCellType().equals(CellType.BLANK)) {
                        continue;
                    } else {
                        // 타입별로 내용 읽기
                        String resultValue = conversionExcelValue(cell, value);

                        fileValues.add(resultValue);
                        fileModelList.put(r, fileValues);
                    }

                }
            }
            inputStream.close();
        }

        return fileModelList;
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
                    value = String.valueOf( dateFormat.format(cell.getDateCellValue()) );
                } else {
                    double doubleVal = cell.getNumericCellValue() ;
//                    DecimalFormat df = new DecimalFormat("###,###");
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
                value = cell.getErrorCellValue() + "";
                break;
        }

        return value;
    }
}