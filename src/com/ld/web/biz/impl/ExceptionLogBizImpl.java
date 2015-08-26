package com.ld.web.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ld.web.bean.Page;
import com.ld.web.bean.model.ExceptionLog;
import com.ld.web.biz.ExceptionLogBiz;
import com.ld.web.dao.ExceptionLogDao;

/**
 * 
 * <p>Title: ExceptionLogBizImpl</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-02-26
 */
@Service
@Transactional
public class ExceptionLogBizImpl implements ExceptionLogBiz {
    @Resource
    private ExceptionLogDao exceptionLogDao;

    @Override
    public Page<ExceptionLog> getPage(Page<ExceptionLog> page) {
        return exceptionLogDao.getPage(page);
    }

}
