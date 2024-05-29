package com.example.companywise.leetcode.microsoft.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Note: This is a companion problem to the System Design problem: Design TinyURL.
 * <p>
 * TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL.
 * <p>
 * There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
 * <p>
 * Implement the Solution class:
 * <p>
 * Solution() Initializes the object of the system.
 * String encode(String longUrl) Returns a tiny URL for the given longUrl.
 * String decode(String shortUrl) Returns the original long URL for the given shortUrl. It is guaranteed that the given shortUrl was encoded by the same object.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: url = "https://leetcode.com/problems/design-tinyurl"
 * Output: "https://leetcode.com/problems/design-tinyurl"
 * <p>
 * Explanation:
 * Solution obj = new Solution();
 * string tiny = obj.encode(url); // returns the encoded tiny url.
 * string ans = obj.decode(tiny); // returns the original url after decoding it.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= url.length <= 104
 * url is guranteed to be a valid URL.
 * <p>
 * Leetcode link : https://leetcode.com/problems/encode-and-decode-tinyurl/description/
 */
public class EncodeAndDecodeTinyURL {

    Map<String, String> map = new HashMap();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String uniqueId = getUniqueId();
        //save the uniqueId
        map.put(uniqueId, longUrl);
        return "http://tinyurl.com/" + uniqueId;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int index = shortUrl.indexOf(".com/");

        //last 6 character as unique id
        String uniqueId = shortUrl.substring(index + 5);
        return map.get(uniqueId);
    }

    private String getUniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(6);
    }
}


