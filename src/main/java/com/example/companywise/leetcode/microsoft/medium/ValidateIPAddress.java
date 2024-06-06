package com.example.companywise.leetcode.microsoft.medium;

/**
 * Given a string queryIP, return "IPv4" if IP is a valid IPv4 address, "IPv6" if IP is a valid IPv6 address or "Neither" if IP is not a correct IP of any type.
 * <p>
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4" where 0 <= xi <= 255 and xi cannot contain leading zeros. For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses while "192.168.01.1", "192.168.1.00", and "192.168@1.1" are invalid IPv4 addresses.
 * <p>
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 * <p>
 * 1 <= xi.length <= 4
 * xi is a hexadecimal string which may contain digits, lowercase English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
 * Leading zeros are allowed in xi.
 * <p>
 * For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334" and "2001:db8:85a3:0:0:8A2E:0370:7334" are valid IPv6 addresses, while "2001:0db8:85a3::8A2E:037j:7334" and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: queryIP = "172.16.254.1"
 * Output: "IPv4"
 * Explanation: This is a valid IPv4 address, return "IPv4".
 * <p>
 * Example 2:
 * <p>
 * Input: queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * Output: "IPv6"
 * Explanation: This is a valid IPv6 address, return "IPv6".
 * <p>
 * Example 3:
 * <p>
 * Input: queryIP = "256.256.256.256"
 * Output: "Neither"
 * Explanation: This is neither a IPv4 address nor a IPv6 address.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * queryIP consists only of English letters, digits and the characters '.' and ':'.
 * <p>
 * Leetcode link : https://leetcode.com/problems/validate-ip-address/description/
 */
public class ValidateIPAddress {
    private static String NEITHER = "Neither";
    public String validIPAddress(String ip) {

        if (ip.length() == 0) return NEITHER;

        if (ip.contains(".")) return validateIPV4(ip);

        if (ip.contains(":")) return validateIPV6(ip);

        return NEITHER;
    }

    private String validateIPV4(String ip) {
        // step 1
        if (ip.charAt(0) == '.' || ip.charAt(ip.length() - 1) == '.') return NEITHER;

        //step 2
        String[] component = ip.split("\\.");

        //step 3
        if (component.length != 4) return NEITHER;

        //step 4
        for (String comp : component) {
            if (comp.length() == 0 || comp.length() > 3 || (comp.charAt(0) == '0' && comp.length() > 1)) {
                return NEITHER;
            }

            //step5
            for (char ch : comp.toCharArray()) {
                if (ch < '0' || ch > '9') return NEITHER;
            }

            //step6
            int num = Integer.parseInt(comp);
            if (num < 0 || num > 255) return NEITHER;

        }

        return "IPv4";
    }

    private String validateIPV6(String ip) {
        if (ip.charAt(0) == ':' || ip.charAt(ip.length() - 1) == ':') return NEITHER;

        String[] component = ip.split(":");

        if (component.length != 8) return NEITHER;

        for (String comp : component) {
            if (comp.length() == 0 || comp.length() > 4) return NEITHER;


            for (char ch : comp.toLowerCase().toCharArray()) {
                if ((ch < '0' || ch > '9') && (ch != 'a' && ch != 'b' && ch != 'c' && ch != 'd' && ch != 'e' && ch != 'f')) {
                    return NEITHER;
                }
            }
        }
        return "IPv6";
    }
}
