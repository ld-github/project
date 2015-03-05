package com.ld.web.biz;

import com.ld.web.bean.User;

/**
 * 
 * <p>Title: UserBiz</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-8
 */
public interface UserBiz {
    /**
     * save user
     * 
     * @param u
     * @return
     */
    boolean saveUser(User u1, User u2);

    /**
     * get user count
     * 
     * @return
     */
    long getUserCount();
}
