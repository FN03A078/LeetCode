package com.LeetCode.com.shan.utils;

import java.util.Scanner;

/**
 * @Author: SHAN
 * @Description:
 * @Date: created in 14:22 2022/6/28
 */
public class Utils {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = scanner.nextLine();
            System.out.println(str.replaceAll(" ",""));
        }
    }
}
