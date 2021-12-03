package com.xiaorui.socket.base.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author：songx
 * @date：2018年10月25日,下午2:46:23
 */
public class StringUtils {
    
    /**
     * 验证字符串是否是空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * 获取UUID
     *
     * @return UUID
     */
    public static String getUUID() {
        return (UUID.randomUUID() + "").replaceAll("-", "");
    }

    /**
     * 首字母转小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     * @param s
     * @return
     */
    public static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 判断数组中的字符是否在字符串中存在(模糊匹配)。如果数组为null则返回false
     */
    public static boolean contains(String str, String... arrays) {
        if (isEmpty(str) || arrays == null) {
            return false;
        }
        for (String array : arrays) {
            if (str.contains(array)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 判断数组中的字符是否在字符串的起始。如果数组为null则返回false
     */
    public static boolean startsWith(String str, String... arrays) {
        if (isEmpty(str) || arrays == null) {
            return false;
        }
        for (String array : arrays) {
            if (str.startsWith(array)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 替换字符串
     */
    public static String replace(String str, String newChar, String... arrays) {
        if (isEmpty(str) || arrays == null) {
            return str;
        }
        for (String array : arrays) {
            if (str.contains(array)) {
                return str.replace(array, newChar);
            }
        }
        return str;
    }
    
    /**
     * 比较字符串,区分大小写
     */
    public static boolean equals(String str, String... arrays) {
        if (isEmpty(str) || arrays == null) {
            return false;
        }
        for (String array : arrays) {
            if (str.equals(array)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 比较字符串,不区分大小写
     */
    public static boolean equalsIgnoreCase(String str, String... arrays) {
        if (isEmpty(str) || arrays == null) {
            return false;
        }
        for (String array : arrays) {
            if (str.equalsIgnoreCase(array)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 校验特殊字符
     */
    public static boolean checkCharacter(String str) {
        if (isEmpty(str)) {
            return false;
        }
        String regEx = "[\\\\\"%/]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
    
}
