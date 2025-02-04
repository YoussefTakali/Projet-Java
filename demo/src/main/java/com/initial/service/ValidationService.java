package com.initial.service;

public class ValidationService {
    public static boolean validateEmail(String email) {
        return email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    public static boolean validatePhone(String phone) {
        return phone.matches("^\\+?[0-9]{10,15}$");
    }

    public static boolean validatePassword(String password) {
        return password.length() >= 8;
    }
}