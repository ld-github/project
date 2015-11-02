package com.ld.web.action.server;

import java.io.File;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.ld.web.action.ServerAction;
import com.ld.web.util.DateUtil;
import com.ld.web.util.FileManager;
import com.ld.web.util.JsonMapper;

@Action(value = "file")
public class FileAction extends ServerAction {

    private static final long serialVersionUID = 4857983949040220967L;

    private static final Logger logger = Logger.getLogger(FileAction.class);

    private static final String UPLOAD_FOLDER = "project-upload";

    private File file;

    /**
     * Struts upload params
     */
    private String fileContentType;

    private String fileFileName;

    /**
     * WebUploader upload params
     */
    private String id;

    private String name;

    private String type;

    private String size;

    private String lastModifiedDate;

    public String upload() throws Exception {
        try {
            logger.info(String.format("Upload file contentType: %s, fileName: %s, fileSize: %s", fileContentType, fileFileName, file.length()));

            String realPath = new File(super.takeRequest().getServletContext().getRealPath("")).getParent();
            logger.info(String.format("Upload get parent path by realPath: %s", realPath));
            String date = DateUtil.formatNow(DateUtil.TEMPORALTYPE_DATE);
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String fileName = uuid + FileManager.getSuffixName(this.fileFileName);
            String destFilePath = realPath + File.separator + UPLOAD_FOLDER + File.separator + date;

            File destFile = new File(destFilePath + File.separator + fileName);
            if (!FileManager.createFile(destFile)) {
                throw new RuntimeException("Create dest file error!");
            }
            FileManager.copyFile(file, destFile);
            logger.info(String.format("Copy file to: %s", destFilePath));

            super.putResult(true, String.format("文件: %s上传成功", fileFileName));
            logger.info(String.format("Upload file %s success", fileFileName));
        } catch (Exception e) {
            super.putResult(false, String.format("文件: %s上传失败", fileFileName));
            super.takeResponse().setStatus(500);
            logger.error(String.format("Upload file %s error by: %s", fileFileName, e.getMessage()), e);
        }
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}
