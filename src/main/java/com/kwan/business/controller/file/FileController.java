package com.kwan.business.controller.file;

import com.kwan.business.model.file.FileModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    /**
     * @title 엑셀파일 읽기
     * @since 2024.01.21
     */
    @PostMapping("/file/readFile")
    public FileModel readFile(@RequestParam("file") MultipartFile file) {

        FileModel fileModel = new FileModel();
        Workbook workbook = null;


        return fileModel;
    }

}
