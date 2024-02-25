package com.mizdooni.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordService {

    private static PasswordService INSTANCE;

    private static final int SALT_LENGTH = 16;

    private PasswordService() {}

    public static PasswordService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PasswordService();
        }

        return INSTANCE;
    }

    public String hash(String password) throws RuntimeException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(salt);

        byte[] hashedPassword = digest.digest(password.getBytes());

        byte[] combined = new byte[salt.length + hashedPassword.length];
        System.arraycopy(salt, 0, combined, 0, salt.length);
        System.arraycopy(hashedPassword, 0, combined, salt.length, hashedPassword.length);

        StringBuilder hexString = new StringBuilder();
        for (byte b : combined) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public boolean verify(String password, String hash) throws NoSuchAlgorithmException {
        byte[] combined = new byte[hash.length() / 2];
        for (int i = 0; i < hash.length(); i += 2) {
            combined[i / 2] = (byte) Integer.parseInt(hash.substring(i, i + 2), 16);
        }

        byte[] salt = new byte[SALT_LENGTH];
        byte[] storedHashedPassword = new byte[combined.length - SALT_LENGTH];
        System.arraycopy(combined, 0, salt, 0, SALT_LENGTH);
        System.arraycopy(combined, SALT_LENGTH, storedHashedPassword, 0, storedHashedPassword.length);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(salt);
        byte[] hashedInputPassword = digest.digest(password.getBytes());

        return MessageDigest.isEqual(storedHashedPassword, hashedInputPassword);
    }
}
