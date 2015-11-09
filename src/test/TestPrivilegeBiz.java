package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ld.web.bean.model.Privilege;
import com.ld.web.biz.PrivilegeBiz;

public class TestPrivilegeBiz {

    private static ApplicationContext application;

    public static void main(String[] args) {
        application = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        PrivilegeBiz privilegeBiz = (PrivilegeBiz) application.getBean("privilegeBizImpl");
        // Privilege p1 = new Privilege(null, "test1", "test1");
        // Privilege p2 = new Privilege(null, "test2", "test2");
        // Privilege p3 = new Privilege(null, "test3", "test3");
        // privilegeBiz.saveOrUpdate(p1);
        // privilegeBiz.saveOrUpdate(p2);
        // privilegeBiz.saveOrUpdate(p3);
        // List<Privilege> items = new ArrayList<Privilege>();
        // items.add(p1);
        // items.add(p2);
        // Privilege p = new Privilege(items, "test", "test");
        // privilegeBiz.saveOrUpdate(p);
        Privilege p = privilegeBiz.get(4L);
        System.out.println(p.getName());
        privilegeBiz.delete(p);
    }
}
