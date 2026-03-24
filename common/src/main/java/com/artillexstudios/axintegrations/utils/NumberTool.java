package com.artillexstudios.axintegrations.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberTool {

    public static BigInteger toBigInteger(Number number) {
        if (number == null) return null;
        if (number instanceof BigInteger) {
            return (BigInteger) number;
        } else if (number instanceof BigDecimal) {
            return ((BigDecimal) number).toBigInteger();
        } else if (number instanceof Long || number instanceof Integer || number instanceof Short || number instanceof Byte) {
            return BigInteger.valueOf(number.longValue());
        } else if (number instanceof Double || number instanceof Float) {
            return BigDecimal.valueOf(number.doubleValue()).toBigInteger();
        } else {
            return new BigInteger(number.toString());
        }
    }

    public static BigDecimal toBigDecimal(Number number) {
        if (number == null) return null;
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        } else if (number instanceof Long || number instanceof Integer || number instanceof Short || number instanceof Byte) {
            return BigDecimal.valueOf(number.longValue());
        } else if (number instanceof Double || number instanceof Float) {
            return BigDecimal.valueOf(number.doubleValue());
        } else {
            return new BigDecimal(number.toString());
        }
    }
}
