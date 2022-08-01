package stringutil;

/**
 * @description:
 * @author: frank.wu
 * @create: 2022-05-07
 */
public class MatchUtil {
    static String regex = "^(162|165|167|170|171)\\d{8}$";
    static String regex2 = "^1(3|4|5|6|7|8|9)\\d{9}$";

    public static void main(String[] args) {
        String s = "13111111111";
        System.out.println(s.matches(regex2));
        System.out.println(s.matches(regex));
    }
}
