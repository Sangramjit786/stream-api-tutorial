package net.javaguides.sys.design.shortenurl;

import java.util.HashMap;
import java.util.Map;

public class URLShortener {

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String DOMAIN = "http://short.ly/";
    private final Map<String, String> map = new HashMap<>(); // short -> long
    private long counter = 1; // auto-increment ID

    // Shorten long URL
    public String shorten(String longUrl) {
        String shortCode = encode(counter++);
        map.put(shortCode, longUrl);
        return DOMAIN + shortCode;
    }

    // Expand short URL back to original
    public String expand(String shortUrl) {
        String code = shortUrl.replace(DOMAIN, "");
        return map.get(code);
    }

    // Encode numeric ID to Base62
    private String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int idx = (int)(num % 62);
            sb.append(BASE62.charAt(idx));
            num /= 62;
        }
        return sb.reverse().toString();
    }

    // Demo
    public static void main(String[] args) {
        URLShortener shortener = new URLShortener();

        String s1 = shortener.shorten("https://example.com/long-url-1");
        String s2 = shortener.shorten("https://openai.com/research/");
        String s3 = shortener.shorten("https://github.com/");

        System.out.println("Shortened URLs:");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println("\nExpanded URLs:");
        System.out.println(shortener.expand(s1));
        System.out.println(shortener.expand(s2));
        System.out.println(shortener.expand(s3));
    }
}
