package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ld.web.bean.model.Manager;
import com.ld.web.util.JsonMapper;

public class TestJsonMapper {

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.setUsername("100");
        String json = JsonMapper.getInstance().toJson(manager);
        System.out.println("json:" + json);
        Manager u = JsonMapper.getInstance().toObject(json, Manager.class);
        u.setPassword("001");
        System.out.println("ac.getRequesttype():" + u.getUsername());

        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "a");
        map.put("2", "3");
        System.out.println("map Json:" + JsonMapper.getInstance().toJson(map));

        List<Manager> list = new ArrayList<Manager>();
        list.add(u);
        list.add(manager);

        String listJson = JsonMapper.getInstance().toJson(list);
        System.out.println("listJson:" + listJson);

        List<Manager> listUser = JsonMapper.getInstance().toObject(listJson, new TypeReference<List<Manager>>() {
        });
        for (Manager lu : listUser) {
            System.out.println(lu.getUsername());
            System.out.println(lu.getPassword());
        }
    }

}
