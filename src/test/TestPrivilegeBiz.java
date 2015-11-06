package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ld.web.biz.PrivilegeBiz;

public class TestPrivilegeBiz {

    private static ApplicationContext application;

    public static void main(String[] args) {
        application = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        PrivilegeBiz privilegeBiz = (PrivilegeBiz) application.getBean("privilegeBizImpl");
        privilegeBiz.truncate();
    }
}
