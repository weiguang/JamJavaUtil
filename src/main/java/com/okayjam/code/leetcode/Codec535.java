package com.okayjam.code.leetcode;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 535. TinyURL 的加密与解密
 *
 * @author JamChen jamchen@tencent.com
 * @date 2026/08/21 10:12
 **/
public class Codec535 {
    private final String baseUrl = "http://tinyurl.com/";
    private final HashMap<String, String> map = new HashMap<>();
    private static AtomicLong INC = new AtomicLong(System.currentTimeMillis());
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String l;
        while (true) {
            l = Long.toHexString(INC.getAndIncrement());
            if (map.containsKey(l)) continue;
            map.putIfAbsent(l, longUrl);
            break;
        }
        return baseUrl + l;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl.replace(baseUrl, ""));
    }
}


// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url))
