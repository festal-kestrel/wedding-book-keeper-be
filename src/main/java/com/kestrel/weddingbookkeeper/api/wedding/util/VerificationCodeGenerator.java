package com.kestrel.weddingbookkeeper.api.wedding.util;

import java.util.concurrent.ThreadLocalRandom;

public class VerificationCodeGenerator {

    private static final int CODE_SIZE = 10;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    private VerificationCodeGenerator() {
    }

    public static String generate() {
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < CODE_SIZE; i++) {
            int index = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(index);
            code.append(randomChar);
        }
        return code.toString();
    }
}
