package test;

import java.util.LinkedHashMap;

/**
 * 
 * <p>Title: TestLinkedHashMap</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-1-15
 */
public class TestLinkedHashMap {
    public static void main(String[] args) {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
        lhm.put("o.id", "asc");
        lhm.put("o.name", "desc");
        StringBuffer sb = new StringBuffer("order by");
        for (String key : lhm.keySet()) {
            sb.append(" ").append(key).append(" ").append(lhm.get(key)).append(",");
        }
        System.out.println(sb.delete(sb.toString().lastIndexOf(","), sb.toString().length()).toString());
    }
}
