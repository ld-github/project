package com.ld.web.biz;

import com.ld.web.bean.User;

public interface UserBiz {
    /**
     * save user
     * 
     * @param u
     * @return
     */
    boolean saveUser(User u1, User u2);
}
