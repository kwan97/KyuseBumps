package com.kwan.business.controller.file;

import com.kwan.business.core.JsonResponseObject;
import com.kwan.business.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * @title 엑셀파일 읽기
     * @since 2024.01.21
     */
    @PostMapping("/file/readFile")
    public JsonResponseObject readFile(JsonResponseObject response, @RequestParam("file") MultipartFile file) throws Exception {

        return fileService.readFile(response, file);
    }

}
