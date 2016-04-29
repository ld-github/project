package com.ld.web.action.server;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;

import com.google.code.kaptcha.Constants;
import com.ld.web.action.ServerAction;
import com.ld.web.bean.Page;
import com.ld.web.bean.model.Manager;
import com.ld.web.biz.ManagerBiz;
import com.ld.web.util.EncryptionUtil;

/**
 * 
 * <p>Title: ManagerAction</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-01-08
 */
@Action(value = "manager")
public class ManagerAction extends ServerAction {

    private static final long serialVersionUID = -4369317987413706899L;

    private static final Logger logger = Logger.getLogger(ManagerAction.class);

    @Resource
    private ManagerBiz managerBiz;

    // The front was introduced into object
    private Manager manager;

    private Page<Manager> page;

    private String kaptcha;

    private int available = -1;

    private int administrator = -1;

    /**
     * Manager login
     * 
     * @return
     * @throws Exception
     */
    public String login() throws Exception {
        logger.info(String.format("Username: %s request login...", manager.getUsername()));

        String kaptcha = (String) super.takeSession().get(Constants.KAPTCHA_SESSION_KEY);
        if (!kaptcha.equals(this.kaptcha)) {
            super.putResult(false, "验证码输入错误");
            return SUCCESS;
        }
        String password = EncryptionUtil.sha256(EncryptionUtil.base64Decode(manager.getPassword()));
        Manager m = managerBiz.login(manager.getUsername(), password);
        if (null == m) {
            super.putResult(false, "账号或密码错误");
            logger.info(String.format("Username: %s login failed...", manager.getUsername()));
            return SUCCESS;
        }
        if (m.getAvailable()) {
            super.putSessionManager(m);
        }
        super.putResult(m.getAvailable(), m.getAvailable() ? "登录成功" : "该账号被禁用");
        return SUCCESS;
    }

    /**
     * Take login manager info
     * 
     * @return
     * @throws Exception
     */
    public String takeLoginManager() throws Exception {
        Manager manager = super.takeSessionManager();
        super.putResult("manager", manager);
        return SUCCESS;
    }

    @Override
    public String getPageRecords() throws Exception {
        try {
            Long exceptId = takeSessionManager().getId();
            manager = null == manager ? new Manager() : manager;
            super.putResult(managerBiz.getPage(exceptId, manager.getUsername(), checkIsAvailable(available), page));
        } catch (Exception e) {
            super.putResult(super.RESULT_PAGE, null);
            logger.error(String.format("Get manager page error: %s", e.getMessage()), e);
        }
        return SUCCESS;
    }

    /**
     * Change manager available
     * 
     * @return
     * @throws Exception
     */
    public String changeAvailable() throws Exception {
        try {
            Manager m = managerBiz.changeAvailable(manager);
            super.putResult(true, "修改成功");
            super.putResult("manager", m);
        } catch (Exception e) {
            super.putResult(false, "修改失败");
            logger.error(String.format("Change manager available error: %s", e.getMessage()), e);
        }
        return SUCCESS;
    }

    /**
     * Get manager
     * 
     * @return
     * @throws Exception
     */
    public String get() throws Exception {
        super.putResult("manager", managerBiz.get(manager.getId()));
        return SUCCESS;
    }

    /**
     * Save Manager
     */
    @Override
    public String save() throws Exception {
        try {
            if (!managerBiz.checkUsername(manager.getUsername())) {
                logger.error(String.format("Save manager info error by username: %s is exist!", manager.getUsername()));
                super.putResult(false, "账号已存在");
                return SUCCESS;
            }

            manager.setPassword(EncryptionUtil.sha256(EncryptionUtil.base64Decode(manager.getPassword())));
            manager.setAdministrator(checkIsAdministrator(this.administrator));
            managerBiz.save(manager);
            super.putResult(true, "保存成功");
        } catch (Exception e) {
            logger.error(String.format("Save manager info error by: %s", e.getMessage()), e);
            super.putResult(false, "保存失败");
        }
        return SUCCESS;
    }

    private Boolean checkIsAvailable(int available) {
        return available > -1 ? available == 1 ? true : false : null;
    }

    private Boolean checkIsAdministrator(int administrator) {
        return administrator > -1 ? administrator == 1 ? true : false : null;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getKaptcha() {
        return kaptcha;
    }

    public void setKaptcha(String kaptcha) {
        this.kaptcha = kaptcha;
    }

    public Page<Manager> getPage() {
        return page;
    }

    public void setPage(Page<Manager> page) {
        this.page = page;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getAdministrator() {
        return administrator;
    }

    public void setAdministrator(int administrator) {
        this.administrator = administrator;
    }

}
