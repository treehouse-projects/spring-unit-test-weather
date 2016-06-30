package com.teamtreehouse.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WebUtils {
    public static String uriEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8")
                    .replaceAll("\\%20", "+")
                    .replaceAll("\\%2C", ",")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }
}
