package com.kwan.business.controller.file;

import com.kwan.business.model.file.FileModel;
import com.kwan.business.service.file.FileService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * @title 엑셀파일 읽기
     * @since 2024.01.21
     */
    @PostMapping("/file/readFile")
    public List<String> readFile(@RequestParam("file") MultipartFile file) throws Exception {

        return fileService.readFile(file);
    }

}
