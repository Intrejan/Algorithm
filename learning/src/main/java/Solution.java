import java.util.*;


public class Solution {
    /**
     * 将输入的十进制数字转换为对应的二进制字符串和十六进制字符串
     * @param number string字符串 十进制数字字符串
     * @return string字符串
     */
    public String changeFormatNumber (String number) {
        // write code here
        int num = 0;
        if(!notNumber(number)){
            return "INPUTERROR";
        }
        try {
            num = Integer.parseInt(number);
            if(num >= 32766){
                return "NODATA";
            }
            if(num <0 && Integer.toHexString(num).charAt(4)!='f'){
                return "NODATA";
            }
        }catch (NumberFormatException e){
            return "NODATA";
        }
        String s1;
        String s2 = "";
        s1 = Integer.toBinaryString(num);
        s1 = "0000000000000000"+s1;
        s1 = s1.substring(s1.length()-16);
        s2 = Integer.toHexString(num);
        s2 = "0000"+s2.toUpperCase();
        s2 = s2.substring(s2.length()-4);
        return s1+","+s2;
    }

    private boolean notNumber(String number) {
        if(number.startsWith("-") || number.startsWith("+")){
            number = number.substring(1);
        }
        for(char c:number.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.changeFormatNumber("-2"));
    }
}
