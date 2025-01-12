package com.kwan.business.service.file;

import com.kwan.business.model.file.FileModel;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    public List<String> readFile(MultipartFile file) throws Exception {

        List<String> fileModelList = new ArrayList<>();

        InputStream inputStream = file.getInputStream();// 파일 읽기
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream); // 엑셀 파일 파싱

        XSSFSheet sheet = workbook.getSheetAt(0); // 엑셀 파일의 첫번째 (0) 시트지
        int rows = sheet.getPhysicalNumberOfRows(); // 행의 수

        for (int r = 1; r < rows; r++) { // 마지막에 건수 있음. 그 줄 제외
            XSSFRow row = sheet.getRow(r); // 0 ~ rows

            if (row != null) { // 행이 비어있지 않을 때
                int cells=row.getPhysicalNumberOfCells(); // 열의 수

                for (int c = 0; c < cells; c++) {
                    XSSFCell cell = row.getCell(c); // 0 ~ cell
                    String value = "";

                    // 12반쩨 열이 강의시간(강의실)
                    // r열 c행의 cell이 비어있을 때 혹은 시간표 열이 아닐때
                    if (cell == null || c != 12 || cell.getCellType().equals(CellType.BLANK)) {
                        continue;
                    } else { // 타입별로 내용 읽기
                        switch (cell.getCellType()) {
                            case FORMULA:
                                value = cell.getCellFormula();
                                break;
                            case NUMERIC:
                                value = cell.getNumericCellValue() + "";
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

                    }
                    fileModelList.add(value);
                }
            }
        }

        return fileModelList;
    }
}
