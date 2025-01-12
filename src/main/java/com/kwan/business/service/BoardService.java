package com.kwan.business.service;

import com.kwan.business.dao.FileUploadDao;
import com.kwan.business.model.file.FileModel;
import com.kwan.business.param.FileParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired(required=true)
    private FileUploadDao fileUploadDao;


    public FileModel getBoard(FileParam param) {
        FileModel fileModel = new FileModel();
        List<FileModel> fileList = fileUploadDao.getBoard(param);

        return fileList.get(0);
    }
}
