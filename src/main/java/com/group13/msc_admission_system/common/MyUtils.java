package com.group13.msc_admission_system.common;

/**
 * This is a custom class to store all useful functions/methods to be used across the entire application.
 */
public class MyUtils {
    public static boolean isNotEmptyAndNotNull(String str) {
        return (str != null && !str.isEmpty());
    }
}