package com.example.authorizer.utils;

public class MCCUtils {

    public static String normalizeMerchantName(String merchantName) {
        return merchantName.trim().toUpperCase();
    }
}
