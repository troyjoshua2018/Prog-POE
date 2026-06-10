/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_part3;

import java.util.Scanner;

public class LoginClass {
    private final Scanner scanner = new Scanner(System.in);

    public boolean authenticate(UserClass registeredUser) {
        if (registeredUser == null) return false;

        while (true) {
            System.out.println("=== LOGIN ===");
            System.out.print("Enter username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String loginPassword = scanner.nextLine();

            if (loginUsername.equals(registeredUser.getUsername()) && loginPassword.equals(registeredUser.getPassword())) {
                System.out.println("\nLogin successful! Welcome " + registeredUser.getUsername());
                return true;
            } else {
                System.out.println("Login failed. Try again.\n");
            }
        }
    }
}