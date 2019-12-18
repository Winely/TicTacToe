package com.dongyihe;

public class Utils {
    private Utils(){}

    public static String repeatString(String str, int count){
        StringBuilder result = new StringBuilder();
        for (int i=0; i < count; i++){
            result.append(str);
        }
        return result.toString();
    }
}
