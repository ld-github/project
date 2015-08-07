package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ld.web.bean.model.User;
import com.ld.web.util.JsonMapper;

public class TestJsonMapper {

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("100");
        String json = JsonMapper.getInstance().toJson(user);
        System.out.println("json:" + json);
        User u = JsonMapper.getInstance().toObject(json, User.class);
        u.setPassword("001");
        System.out.println("ac.getRequesttype():" + u.getUsername());

        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "a");
        map.put("2", "3");
        System.out.println("map Json:" + JsonMapper.getInstance().toJson(map));

        List<User> list = new ArrayList<User>();
        list.add(u);
        list.add(user);

        String listJson = JsonMapper.getInstance().toJson(list);
        System.out.println("listJson:" + listJson);

        List<User> listUser = JsonMapper.getInstance().toObject(listJson, new TypeReference<List<User>>() {
        });
        for (User lu : listUser) {
            System.out.println(lu.getUsername());
            System.out.println(lu.getPassword());
        }
    }

}
