package com.kafkas.common;

/**
 * Created by wushenjun on 17-2-23.
 */
public class TestForBreak {
    public static void main(String args[]) {

        String[] strs = {"1", "2", "0", "3"};
        String value = "123";
        int index = 0;
        for(String str : strs) {
            index = value.indexOf(str);
            if(index<0) {
                break;
            }
            System.out.println(str+"   " + index);
        }

        System.out.println(index);

    }
}
