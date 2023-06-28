package com.kestrel.weddingbookkeeper.api.wedding.util;

import java.util.concurrent.ThreadLocalRandom;

public class AuthorizationCodeGenerator {

    private static final int CODE_LENGTH = 10;
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    private AuthorizationCodeGenerator() {
    }

    public static String generateQrCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            code.append(randomChar);
        }
        return code.toString();
    }
}
