package test;

import java.util.Vector;

/**
 * 
 * <p>Title: TestVector</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-3-2
 */
public class TestVector {
    public static void main(String[] args) {
        Vector<String> strs = new Vector<String>();
        strs.add("a");
        strs.add("b");
        strs.add("c");
        strs.add("d");
        strs.add("e");
        strs.add("f");
        strs.addElement("g");
        for (String str : strs) {
            System.out.println(str);
        }
    }
}
