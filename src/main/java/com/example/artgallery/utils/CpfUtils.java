package com.example.artgallery.utils;

public final class CpfUtils {

    private CpfUtils() {
    }

    public static boolean isValid(String cpf) {

        cpf = removeFormatting.removeCpfFormatting(cpf);

        if (cpf == null || cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int firstDigit = calculateDigit(cpf, 9);
        int secondDigit = calculateDigit(cpf, 10);

        return firstDigit == Character.getNumericValue(cpf.charAt(9))
                && secondDigit == Character.getNumericValue(cpf.charAt(10));
    }

    private static int calculateDigit(String cpf, int length) {
        int sum = 0;

        for (int i = 0; i < length; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            sum += digit * (length + 1 - i);
        }

        int remainder = sum % 11;

        return remainder < 2 ? 0 : 11 - remainder;
    }
}
