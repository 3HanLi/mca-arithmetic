package com.wy.mca.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串相乘：https://leetcode-cn.com/problems/multiply-strings/solution/zi-fu-chuan-xiang-cheng-by-leetcode-solution/
 * @author boss
 */
public class StrMultiply {

    private static Map<Character,Integer> charNumberMap = new HashMap<>();

    private static final String ZERO = "0";

    static {
        charNumberMap.put('0',0);
        charNumberMap.put('1',1);
        charNumberMap.put('2',2);
        charNumberMap.put('3',3);
        charNumberMap.put('4',4);
        charNumberMap.put('5',5);
        charNumberMap.put('6',6);
        charNumberMap.put('7',7);
        charNumberMap.put('8',8);
        charNumberMap.put('9',9);
    }

    public static void main(String[] args) {
        String multiply = multiplyError("123", "2");
        System.out.println(multiply);

        String s = multiply("123456789", "1234");
        System.out.println(s);
    }

    public static String multiply(String num1, String num2) {
        if (num1.equals(ZERO) || num2.equals(ZERO)) {
            return "0";
        }
        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            ans = addStrings(ans, curr.reverse().toString());
        }
        return ans;
    }

    public static String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }

    /**
     * 错误的解法
     * @param num1
     * @param num2
     * @return
     */
    public static String multiplyError(String num1, String num2) {
        Long n1 = convertStrToNumber(num1);
        Long n2 = convertStrToNumber(num2);
        return n1 * n2 + "";
    }

    private static Long convertStrToNumber(String str){
        char[] chars = str.toCharArray();
        Long number = 0L;
        for (int i=0; i<chars.length; i++){
            number += convertStrToNumber(chars[i],chars.length-i-1);
        }
        return number;
    }

    private static Long convertStrToNumber(char c, int len){
        Long number = charNumberMap.get(c).longValue();
        for (int i=0; i<len; i++){
            number *= 10;
        }
        return number;
    }

}
