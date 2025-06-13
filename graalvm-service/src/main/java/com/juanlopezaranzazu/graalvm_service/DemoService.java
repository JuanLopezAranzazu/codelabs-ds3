package com.juanlopezaranzazu.graalvm_service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DemoService {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int start = 0;
        Map<Character, Integer> seen = new HashMap<>();

        for (int end = 0; end < n; end++) {
            char currentChar = s.charAt(end);
            if (seen.containsKey(currentChar) && seen.get(currentChar) >= start) {
                start = seen.get(currentChar) + 1;
            }
            seen.put(currentChar, end);
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }
}
