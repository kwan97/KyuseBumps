package com.kwan.business.controller.board;

import com.kwan.business.model.file.FileModel;
import com.kwan.business.param.FileParam;
import com.kwan.business.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardRestController {

    @Autowired
    public BoardService boardService;

    @RequestMapping("/file/readFile")
    public FileModel getBoard(FileParam param) {

        return boardService.getBoard(param);
    }
}
