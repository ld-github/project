package com.ld.web.biz;

import java.util.List;

import com.ld.web.bean.model.Privilege;

/**
 * 
 *<p>Title: PrivilegeBiz</p>
 *<p>Copyright: Copyright (c) 2015</p>
 *<p>Description: 权限业务逻辑处理</p>
 *
 *@author LD
 *
 *@date 2015-11-04
 */
public interface PrivilegeBiz {

    /**
     * Init Privilege
     * 
     * @return
     */
    void init() throws Exception;

    /**
     * Save or update Privilege
     * 
     * @param privilege
     * @return
     */
    boolean saveOrUpdate(Privilege privilege);

    /**
     * Get all Privilege
     * 
     * @return
     */
    List<Privilege> getAll();

    /**
     * Get Privilege by id
     * 
     * @param id
     * @return
     */
    Privilege get(Long id);

    /**
     * Delete Privilege
     * 
     * @param privilege
     * @return
     */
    boolean delete(Privilege privilege);

    /**
     * Truncate table Privilege
     */
    void truncate();
}
