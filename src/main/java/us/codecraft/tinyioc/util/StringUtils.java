package us.codecraft.tinyioc.util;

public class StringUtils {
    public static boolean isNotEmpt(String str) {
        return null != str && str.length() != 0;
    }
}
