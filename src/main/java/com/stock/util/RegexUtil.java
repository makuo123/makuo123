package com.stock.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    public static final String INPUT = "sd{{222}}ff{{33}}";

    public static void main(String[] args) {
        String regex = "\\{\\{(.*?)\\}\\}+";
//        String regex = "^sd(.*)dd$";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(INPUT);
        while (matcher.find()){
            System.out.println(matcher.group(1));
        }
    }

    // 以{{开头以}}结尾正则
    public static final String REGEX_START_END = "\\{\\{(.*?)\\}\\}+";
    // 获取指定字符中间的数据
}
