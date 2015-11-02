package com.ld.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.bean.model.Attachment;
import com.ld.web.biz.AttachmentBiz;
import com.ld.web.dao.AttachmentDao;

/**
 * 
 *<p>Title: AttachmentBizImpl</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: 附件业务处理</p>
 *
 *@author LD
 *
 *@date 2015-11-02
 */
@Service
@Transactional
public class AttachmentBizImpl implements AttachmentBiz {

    @Resource
    private AttachmentDao attachmentDao;

    @Override
    public void save(Attachment attachment) {
        attachmentDao.save(attachment);
    }

}
