package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhihuianxin.pojo.AccountCheck;
import com.zhihuianxin.util.JsonMapper;

public class TestJsonMapper {

    public static void main(String[] args) {
        AccountCheck accountCheck = new AccountCheck();
        accountCheck.setRequesttype("100");
        String json = JsonMapper.getInstance().toJson(accountCheck);
        System.out.println("json:" + json);
        AccountCheck ac = JsonMapper.getInstance().toObject(json, AccountCheck.class);
        ac.setVer("001");
        System.out.println("ac.getRequesttype():" + ac.getRequesttype());

        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "a");
        map.put("2", "3");
        System.out.println("map Json:" + JsonMapper.getInstance().toJson(map));

        List<AccountCheck> list = new ArrayList<AccountCheck>();
        list.add(ac);
        list.add(accountCheck);

        String listJson = JsonMapper.getInstance().toJson(list);
        System.out.println("listJson:" + listJson);

        List<AccountCheck> listAccountCheck = JsonMapper.getInstance().toObject(listJson,
                new TypeReference<List<AccountCheck>>() {
                });
        for (AccountCheck a : listAccountCheck) {
            System.out.println(a.getRequesttype());
            System.out.println(a.getVer());
        }
    }

}
