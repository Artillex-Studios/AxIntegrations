package com.artillexstudios.axintegrations.utils;

import java.math.BigDecimal;

public class NumberTool {

    public static BigDecimal toBigDecimal(Number number) {
        if (number == null) return null;
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        } else if (number instanceof Long || number instanceof Integer) {
            return BigDecimal.valueOf(number.longValue());
        } else if (number instanceof Double || number instanceof Float) {
            return BigDecimal.valueOf(number.doubleValue());
        } else {
            return new BigDecimal(number.toString());
        }
    }
}
