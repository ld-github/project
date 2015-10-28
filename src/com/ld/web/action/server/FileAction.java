package com.ld.web.action.server;

import org.apache.struts2.convention.annotation.Action;

import com.ld.web.action.ServerAction;
import com.ld.web.util.JsonMapper;

@Action(value = "file")
public class FileAction extends ServerAction {

    private static final long serialVersionUID = 4857983949040220967L;

    public String upload() throws Exception {
        super.putResult(true, "上传文件成功");
        super.writeString(JsonMapper.getInstance().toJson(super.getResult()));
        return NONE;
    }
}
