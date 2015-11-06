package com.ld.web.action.server;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

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
public class FileAction extends ServerAction {

    private static final long serialVersionUID = 4857983949040220967L;

    private static final Logger logger = Logger.getLogger(FileAction.class);

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

    public String upload() throws Exception {
        try {
            logger.info(String.format("Upload file contentType: %s, fileName: %s, fileSize: %s", fileContentType, fileFileName, file.length()));

            String realPath = new File(super.takeRequest().getServletContext().getRealPath("")).getParent();
            logger.info(String.format("Upload get parent path by realPath: %s", realPath));

            String projectName = super.takeRequest().getContextPath().substring(1);
            String date = DateUtil.formatNow(DateUtil.TEMPORALTYPE_DATE);
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String filename = uuid + FileManager.getSuffixName(this.fileFileName);
            String fileDir = File.separator + projectName + "-" + UPLOAD_FOLDER + File.separator + date + File.separator;

            String destFileDir = realPath + fileDir;
            String destFilePath = destFileDir + filename;

            String requestUrl = fileDir + filename;

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

}
