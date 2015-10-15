package com.ld.web.quartz;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionException;

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
        logger.info("Server timed tasks started...");
    }
}
