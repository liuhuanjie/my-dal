package com.ctrip.demo.util;

import com.ctrip.demo.entity.Dalservicetable;

import java.util.Random;

public class RandomGenerator {

    private static Random random = new Random();

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();

    }

    public static int getRandomInt() {
        return random.nextInt();
    }

    public static Dalservicetable getTableInstance() {
        Dalservicetable dalservicetable = new Dalservicetable();
        dalservicetable.setName("1");
        dalservicetable.setAge(1);

        return dalservicetable;
    }
}
