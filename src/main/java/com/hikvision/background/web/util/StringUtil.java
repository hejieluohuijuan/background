package com.hikvision.background.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: hejie
 * @date: 2019/10/8 14:48
 * @UpdateRemark: 修改内容
 * @Description: 该内容仅供海康威视有限公司内部传阅
 * @Version: 1.0
 */
public class StringUtil {
        private static Pattern linePattern = Pattern.compile("_(\\w)");
        /**下划线转驼峰*/
        public static String lineToHump(String str){
            str = str.toLowerCase();
            Matcher matcher = linePattern.matcher(str);
            StringBuffer sb = new StringBuffer();
            while(matcher.find()){
                matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            }
            matcher.appendTail(sb);
            return sb.toString();
        }
        /**驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})*/
        public static String humpToLine(String str){
            return str.replaceAll("[A-Z]", "_$0").toLowerCase();
        }
        private static Pattern humpPattern = Pattern.compile("[A-Z]");
        /**驼峰转下划线,效率比上面高*/
        public static String humpToLine2(String str){
            Matcher matcher = humpPattern.matcher(str);
            StringBuffer sb = new StringBuffer();
            while(matcher.find()){
                matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
            }
            matcher.appendTail(sb);
            return sb.toString();
        }
        public static void main(String[] args) {
            String lineToHump = lineToHump("f_parent_no_leader");
            System.out.println(lineToHump);//fParentNoLeader
            System.out.println(humpToLine(lineToHump));//f_parent_no_leader
            System.out.println(humpToLine2(lineToHump));//f_parent_no_leader
        }
}
