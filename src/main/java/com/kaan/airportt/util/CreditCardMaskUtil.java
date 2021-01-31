package com.kaan.airportt.util;

public class CreditCardMaskUtil {
    public static String maskCreditCardNumber(String creditCardNumber) {
        char delimeter = creditCardNumber.charAt(4);
        if (!Character.isDigit(delimeter)){
            creditCardNumber = creditCardNumber.replaceAll(String.valueOf(delimeter),"");
        }

        return creditCardNumber.replaceAll("\\b(\\d{6})(\\d{6})(\\d{4})", "$1******$3");
    }
}
