package com.ld.web.action.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.ld.web.action.ServerAction;
import com.ld.web.bean.model.Attachment;
import com.ld.web.biz.AttachmentBiz;
import com.ld.web.util.DateUtil;
import com.ld.web.util.FileManager;
import com.ld.web.util.JsonMapper;

/**
 * 
 *<p>Title: FileAction</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: 文件控制器,上传下载</p>
 *
 *@author LD
 *
 *@date 2015-11-02
 */
@Action(value = "file")
@Results({ @Result(type = "stream", 
        name = FileAction.RESULT_STREAM, 
        params = { "contentType", "application/octet-stream;charset=ISO8859-1", "inputName", "inputStream", 
            "contentDisposition", "attachment;filename=\"${fileFileName}\"", "bufferSize", "1024", 
            "contentLength", "\"${contentLength}\"", "allowCaching", "false" }) 
})
public class FileAction extends ServerAction {

    private static final long serialVersionUID = 4857983949040220967L;

    private static final Logger logger = Logger.getLogger(FileAction.class);

    public static final String RESULT_STREAM = "stream";

    private static final String UPLOAD_FOLDER = "upload";

    @Resource
    private AttachmentBiz attachmentBiz;

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

    /**
     * Upload other params
     */
    private String title; // 标题

    private String alt; // 属性

    private String remark; // 备注

    /**
     * Download file params
     */
    private String downloadFilePath;

    private InputStream inputStream; // 文件流

    private long contentLength; // 文件长度

    /**
     * Upload File
     * 
     * @return
     * @throws Exception
     */
    public String upload() throws Exception {
        try {
            logger.info(String.format("Upload file contentType: %s, fileName: %s, fileSize: %s", fileContentType, fileFileName, file.length()));

            String mainPath = new File(super.takeRequest().getServletContext().getRealPath("")).getParent();
            logger.info(String.format("Upload get parent path by servletContext realPath: %s", mainPath));

            String projectName = super.takeRequest().getContextPath().substring(1);
            String date = DateUtil.formatNow(DateUtil.TEMPORALTYPE_DATE);
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String filename = uuid + FileManager.getSuffixName(this.fileFileName);

            String requestfileDir = File.separator + projectName + "-" + UPLOAD_FOLDER + File.separator + date + File.separator;
            String destFileDir = mainPath + requestfileDir;
            String destFilePath = destFileDir + filename;

            String requestUrl = requestfileDir + filename;

            File destFile = new File(destFileDir + filename);
            if (!FileManager.createFile(destFile)) {
                throw new RuntimeException("Create dest file error!");
            }
            if (!FileManager.copyFile(file, destFile)) {
                throw new RuntimeException("Copy destFile from srcFile error!");
            }
            logger.info(String.format("Copy file to: %s", destFileDir));

            Date createDatetime = DateUtil.parseNow(DateUtil.TEMPORALTYPE_TIMESTAMP);
            Attachment att = new Attachment(filename, fileFileName, destFilePath, requestUrl, destFileDir, title, alt, remark, createDatetime);

            attachmentBiz.save(att);
            super.putResult(true, String.format("文件: %s上传成功", fileFileName));
            super.putResult("attachment", att);
            logger.info(String.format("Upload file %s success", fileFileName));
        } catch (Exception e) {
            super.takeResponse().setStatus(500);
            super.putResult(false, String.format("文件: %s上传失败", fileFileName));
            logger.error(String.format("Upload file %s error by: %s", fileFileName, e.getMessage()), e);
        }
        super.writeString(JsonMapper.getInstance().toJson(super.getResult()));
        return NONE;
    }

    /**
     * Download File
     * 
     * @return
     * @throws Exception
     */
    public String download() throws Exception {
        logger.info(String.format("File download path: %s", downloadFilePath));

        File file = getFile(downloadFilePath);
        if (!file.exists()) {
            logger.error(String.format("File: %s not exist!", downloadFilePath));
            return NONE;
        }
        contentLength = file.length();
        fileFileName = file.getName();
        inputStream = new FileInputStream(file);

        return RESULT_STREAM;
    }

    /**
     * Get file by downloadFilePath
     * 
     * @param downloadFilePath
     * @return
     */
    private File getFile(String downloadFilePath) {
        String mainPath = new File(super.takeRequest().getServletContext().getRealPath("")).getParent();
        return new File(mainPath + downloadFilePath);
    }

    /**
     * Check file exist
     * 
     * @return
     * @throws Exception
     */
    public String checkFileExist() throws Exception {
        File file = getFile(downloadFilePath);
        super.putResult(file.exists(), file.exists() ? "文件存在" : "文件不存在");
        return SUCCESS;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDownloadFilePath() {
        return downloadFilePath;
    }

    public void setDownloadFilePath(String downloadFilePath) {
        this.downloadFilePath = downloadFilePath;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

}
