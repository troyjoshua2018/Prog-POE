/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_part3;

import java.util.Scanner;

public class RegistrationClass {
    private final Scanner scanner = new Scanner(System.in);

    public UserClass registerUser() {
        String username, password, phoneNumber;

        while (true) {
            System.out.print("Enter a username (must contain '_' and be max 5 characters): ");
            username = scanner.nextLine();
            if (isValidUsername(username)) {
                System.out.println("Username successfully captured!\n");
                break;
            }
            System.out.println("Invalid username. Try again.\n");
        }

        while (true) {
            System.out.print("Enter a password (8+ chars, uppercase, number, special char): ");
            password = scanner.nextLine();
            if (isValidPassword(password)) {
                System.out.println("Password successfully captured!\n");
                break;
            }
            System.out.println("Invalid password. Try again.\n");
        }

        while (true) {
            System.out.print("Enter phone number (e.g. +271234567890): ");
            phoneNumber = scanner.nextLine();
            if (isValidPhoneNumber(phoneNumber)) {
                System.out.println("Phone number successfully captured!\n");
                break;
            }
            System.out.println("Invalid phone number. Try again.\n");
        }

        System.out.println("=== Registration Complete ===\n");
        return new UserClass(username, password, phoneNumber);
    }

    public static boolean isValidUsername(String username) {
        return username != null && username.length() <= 5 && username.contains("_");
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 8) return false;
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^\\+27\\d{9}$");
    }
}