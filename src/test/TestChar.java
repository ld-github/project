package test;

/**
 * 
 * <p>Title: TestChar</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Description:</p>
 *
 * @author LD
 *
 * @date 2015-3-2
 */
public class TestChar {
    public static void main(String[] args) {
        try {
            char[] ch = new char[] { 'a', 'b', 'c' };
            char[] aa = new char[26];
            for (int i = 0; i < ch.length; i++) {
                aa[ch[i] - 'a'] = 1;
            }
            String str = "";
            for (int i = 0; i < 26; i++) {
                if (aa[i] == 0) {
                    char c = (char) ('a' + i);
                    str = str + c;
                }
            }
            System.out.print(str + "\t");
            throw new Exception("error");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }
}
