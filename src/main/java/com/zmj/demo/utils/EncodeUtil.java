package com.zmj.demo.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class EncodeUtil {
    public static String encodeUTF8(String string){
        try {
            return URLEncoder.encode(string, "UTF-8").replace("+", "%20");
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return null;
        }
    }
}
