package com.ld.web.action.server;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.ld.web.action.ServerAction;
import com.ld.web.util.JsonMapper;

@Action(value = "file")
public class FileAction extends ServerAction {

    private static final long serialVersionUID = 4857983949040220967L;

    private static final Logger logger = Logger.getLogger(FileAction.class);

    private File file;

    private String fileContentType;

    private String fileFileName;

    public String upload() throws Exception {
        logger.info(String.format("Upload file fileContentType: %s, fileFileName: %s, fileSize: %s", fileContentType, fileFileName, file.length()));

        super.putResult(true, "上传文件成功");
        super.writeString(JsonMapper.getInstance().toJson(super.getResult()));
        return NONE;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

}
