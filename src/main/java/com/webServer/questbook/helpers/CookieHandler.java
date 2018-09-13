//package com.backend.webServer.helpers;
//
//import com.sun.net.httpserver.HttpExchange;
//
//import java.time.Duration;
//import java.time.OffsetDateTime;
//import java.time.ZoneOffset;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//public class CookieHandler {
//
//    public static String getSessionId(HttpExchange httpExchange) {
//
//        String cookie = httpExchange.getRequestHeaders().getFirst("Cookie");
//
//        if (cookie != null) {
//            cookie = parseCookie(cookie).get("sessionId");
//        }
//
//        return cookie;
//    }
//
//    public static String getSessionStatus(HttpExchange httpExchange) {
//
//        String cookie = httpExchange.getRequestHeaders().getFirst("Cookie");
//
//        if (cookie != null) {
//            cookie = parseCookie(cookie).get("sessionStatus");
//        }
//
//        return cookie;
//    }
//
//    public static String setNewSessionId(HttpExchange httpExchange) {
//
//        String sessionId = PasswordHash.getSalt();
//        String cookie = "sessionId=" + sessionId + createExpireString();
//
//        httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
//        return sessionId;
//    }
//
//    public static void setStatusToLoggedOut(HttpExchange httpExchange) {
//
//        String cookie = "sessionStatus=loggedOut" + createExpireString();
//
//        httpExchange.getResponseHeaders().add("Set-Cookie",cookie);
//    }
//
//    public static void setStatusToLoggedIn(HttpExchange httpExchange) {
//
//        String cookie = "sessionStatus=loggedIn" + createExpireString();
//
//        httpExchange.getResponseHeaders().add("Set-Cookie", cookie);
//    }
//
//    private static Map<String, String> parseCookie(String cookie) {
//
//        Map<String, String> cookieMap = new HashMap<>();
//
//        for (String data : cookie.split("; ")) {
//
//            String[] entry = data.split("=");
//
//            if (entry.length == 2) {
//                cookieMap.put(entry[0], entry[1]);
//            } else {
//                cookieMap.put(entry[0], null);
//            }
//        }
//        return cookieMap;
//    }
//
//    public static void clearCookie(HttpExchange httpExchange) {
//
//        httpExchange.getResponseHeaders().add("Set-Cookie", "sessionId=");
//        httpExchange.getResponseHeaders().add("Set-Cookie", "sessionStatus=");
//    }
//
//    private static String createExpireString() {
//
//        OffsetDateTime veryLongTime = OffsetDateTime.now(ZoneOffset.UTC).plus(Duration.ofDays(9999));
//
//        String cookieExpireTime = DateTimeFormatter.RFC_1123_DATE_TIME.format(veryLongTime);
//
//        return "; expires= " + cookieExpireTime;
//    }
//}
