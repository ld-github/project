package com.ld.web.quartz;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.quartz.JobExecutionException;

import com.ld.web.enumeration.ExceptionType;

/**
 * 
 *<p>Title: ServerJob</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: 服务器定时任务</p>
 *
 *@author LD
 *
 *@date 2015-10-15
 */
public class ServerJob {

    private static final Logger logger = Logger.getLogger(ServerJob.class);

    public void work() throws JobExecutionException {
        try {
            logger.info("Server timed tasks start up...");
            MDC.put(ExceptionType.EXCEPTION_TYPE, ExceptionType.SYSTEM.value());
        } catch (Exception e) {
            logger.error(String.format("Server timed tasks error by: %s", e.getMessage()), e);
        }
    }
}
