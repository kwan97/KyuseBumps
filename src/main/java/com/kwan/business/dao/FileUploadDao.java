package com.kwan.business.dao;

import com.kwan.business.model.file.FileModel;
import com.kwan.business.param.file.FileParam;

import java.util.List;

public interface FileUploadDao {

    public List<FileModel> getBoard(FileParam param);
}
