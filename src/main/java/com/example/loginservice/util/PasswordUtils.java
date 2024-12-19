package com.example.loginservice.util;

import org.apache.commons.lang3.RandomStringUtils;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class PasswordUtils {
    public static String generateSalt() {
        return RandomStringUtils.randomAlphanumeric(16);
    }

    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((password + salt).getBytes(StandardCharsets.UTF_8));
            byte[] hashedBytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
