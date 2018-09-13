//package com.backend.webServer.helpers;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import java.util.Random;
//
//public class PasswordHash {
//
//    public static String hash(String originalString, String salt) {
//
//        String hashed = DigestUtils.sha256Hex(salt + originalString);
//
//        return hashed;
//    }
//
//    public static String getSalt() {
//
//        int leftLimit = 97; // letter 'a'
//        int rightLimit = 122; // letter 'z'
//        int targetStringLength = 20;
//
//        Random random = new Random();
//        StringBuilder buffer = new StringBuilder(targetStringLength);
//
//        for (int i = 0; i < targetStringLength; i++) {
//            int randomLimitedInt = leftLimit +
//                    (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
//            buffer.append((char) randomLimitedInt);
//        }
//
//        return buffer.toString();
//    }
//
//}
