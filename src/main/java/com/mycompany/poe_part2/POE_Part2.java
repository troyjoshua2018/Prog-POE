/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.poe_part2;

import java.util.Scanner;

public class POE_Part2 {

    private static String username;
    private static String password;
    private static String phoneNumber;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ================= REGISTRATION =================

        while (true) {
            System.out.print("Enter a username (must contain '_' and be max 5 characters): ");
            username = scanner.nextLine();

            if (isValidUsername(username)) {
                System.out.println("Username successfully captured!\n");
                break;
            } else {
                System.out.println("Invalid username. Try again.\n");
            }
        }

        while (true) {
            System.out.print("Enter a password (8+ chars, uppercase, number, special char): ");
            password = scanner.nextLine();

            if (isValidPassword(password)) {
                System.out.println("Password successfully captured!\n");
                break;
            } else {
                System.out.println("Invalid password. Try again.\n");
            }
        }

        while (true) {
            System.out.print("Enter phone number (e.g. +271234567890): ");
            phoneNumber = scanner.nextLine();

            if (isValidPhoneNumber(phoneNumber)) {
                System.out.println("Phone number successfully captured!\n");
                break;
            } else {
                System.out.println("Invalid phone number. Try again.\n");
            }
        }

        System.out.println("=== Registration Complete ===\n");

        // ================= LOGIN =================

        while (true) {
            System.out.println("=== LOGIN ===");

            System.out.print("Enter username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = scanner.nextLine();

            if (loginUsername.equals(username) && loginPassword.equals(password)) {

                System.out.println("\nLogin successful! Welcome " + username);

                // 🔥 CONNECT TO QUICKCHAT HERE
                new QuickChat().start(username, phoneNumber);

                break;

            } else {
                System.out.println("Login failed. Try again.\n");
            }
        }

        scanner.close();
    }

    // ================= VALIDATION =================

    public static boolean isValidUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    public static boolean isValidPassword(String password) {
        if (password.length() < 8) return false;

        boolean hasUppercase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUppercase = true;
            if (Character.isDigit(c)) hasDigit = true;
            if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasUppercase && hasDigit && hasSpecial;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+27\\d{9}$");
    }
}