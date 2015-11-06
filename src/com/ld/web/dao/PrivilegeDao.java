package com.ld.web.dao;

import com.ld.web.bean.model.Privilege;

/**
 * 
 *<p>Title: PrivilegeDao</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2015-11-04
 */
public interface PrivilegeDao extends BaseDao<Privilege> {

    /**
     * Turncate table
     * 
     * @return
     */
    void truncate();
}
