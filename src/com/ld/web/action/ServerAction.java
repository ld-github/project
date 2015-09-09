package com.ld.web.action;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.ld.web.bean.model.Manager;

/**
 * 
 * <p>Title: ServerAction</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-21
 */
@ParentPackage("server-default")
public class ServerAction extends BaseAction {

    private static final long serialVersionUID = -5068058069236790380L;

    public static final String SESSION_MANAGER = "sessionManager";

    /**
     * Put manager to session
     * 
     * @param u
     */
    public void putSessionManager(Manager u) {
        takeSession().put(SESSION_MANAGER, u);
    }

    /**
     * Take manager from session
     * 
     * @return
     */
    public Manager takeSessionManager() {
        return takeSession().containsKey(SESSION_MANAGER) ? (Manager) takeSession().get(SESSION_MANAGER) : null;
    }
}
