package com.serverless.letspoll.commons;

import java.util.Random;

/**
 * Created by Webonise on 09/09/18.
 */
public class RandomIdGenerator {

    public static String getRandomString(String prefix) {
        String mixedBag = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * mixedBag.length());
            salt.append(mixedBag.charAt(index));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append("-");
        sb.append(salt.toString());
        return sb.toString();

    }


}
