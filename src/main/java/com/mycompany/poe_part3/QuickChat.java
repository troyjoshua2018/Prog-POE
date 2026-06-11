/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_part3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuickChat {
    // Parallel Array Structures 
    private final ArrayList<String> messageIDs = new ArrayList<>();
    private final ArrayList<String> recipientsOrDevelopers = new ArrayList<>();
    private final ArrayList<String> messageTexts = new ArrayList<>();
    private final ArrayList<String> flags = new ArrayList<>();
    private final ArrayList<String> messageHashes = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    public void start(String username, String phoneNumber) {
        // Preloading standard assignment Test Data (Messages 1 to 5) for evaluation
        preloadTestData();
        startMenu();
    }

    private void preloadTestData() {
        // Message 1-3 Simulation Placeholders 
        addMessageRecord("000001", "0821112222", "Did you get the cake?", "Sent", "hash1");
        addMessageRecord("000002", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Sent", "hash2");
        addMessageRecord("000003", "+27838884567", "Testing message sequence three", "Sent", "hash3");
        
        // Message 4 
        addMessageRecord("0838884567", "0838884567", "It is dinner time !", "Sent", "hash4");
        
        // Message 5 
        addMessageRecord("000005", "+27838884567", "Ok, I am leaving without you.", "Stored", "hash5");
    }

    // Helper to keep parallel array indexes uniform
    public void addMessageRecord(String id, String entity, String text, String flag, String hash) {
        messageIDs.add(id);
        recipientsOrDevelopers.add(entity);
        messageTexts.add(text);
        flags.add(flag);
        messageHashes.add(hash);
    }

    private void startMenu() {
        while (true) {
            System.out.println("\n=== QUICKCHAT MENU ===");
            System.out.println("1. Add New Messages");
            System.out.println("2. Display All Sent Messages");
            System.out.println("3. Display Longest Message");
            System.out.println("4. Search by Message ID");
            System.out.println("5. Search Messages by Recipient");
            System.out.println("6. Delete Message via Hash");
            System.out.println("7. Display Report");
            System.out.println("8. Quit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1 -> handleAddNewMessages();
                case 2 -> displayAllSentMessages();
                case 3 -> System.out.println("\nLongest Message:\n" + getLongestMessage());
                case 4 -> {
                    System.out.print("Enter Message ID: ");
                    String id = scanner.nextLine();
                    System.out.println("\nResult: " + searchByMessageID(id));
                }
                case 5 -> {
                    System.out.print("Enter Recipient Number: ");
                    String rec = scanner.nextLine();
                    System.out.println("\nResults:\n" + searchByRecipient(rec));
                }
                case 6 -> {
                    System.out.print("Enter Hash to delete: ");
                    String hash = scanner.nextLine();
                    System.out.println("\n" + deleteMessageByHash(hash));
                }
                case 7 -> displayReport();
                case 8 -> {
                    System.out.println("Exiting App...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // --- ADD NEW MESSAGES FLOW ---
    private void handleAddNewMessages() {
        System.out.print("How many messages would you like to add? ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int count = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Adding Message " + (i + 1) + " of " + count + " ---");
            
            System.out.print("Enter Recipient/Developer Number: ");
            String cell = scanner.nextLine();
            
            System.out.print("Enter Message Content: ");
            String body = scanner.nextLine();

            System.out.println("Select Message Status Flag:");
            System.out.println("1. Sent");
            System.out.println("2. Stored");
            System.out.print("Choice: ");
            
            String statusFlag = "Sent";
            if (scanner.hasNextInt()) {
                int flagChoice = scanner.nextInt();
                scanner.nextLine();
                if (flagChoice == 2) {
                    statusFlag = "Stored";
                }
            } else {
                scanner.nextLine();
            }

            // Generate values required for a complete profile instance
            String generatedID = String.valueOf(100000 + random.nextInt(900000));
            String generatedHash = "hash" + String.valueOf(random.nextInt(10000));

            // Record uniformly inside the core storage vectors
            addMessageRecord(generatedID, cell, body, statusFlag, generatedHash);
            System.out.println("Message successfully captured with ID: " + generatedID + " [" + statusFlag + "]");
        }
    }

    // --- DISPLAY ALL SENT MESSAGES ---
    public void displayAllSentMessages() {
        System.out.println("\n=== ALL SENT MESSAGES ===");
        boolean foundSent = false;

        for (int i = 0; i < messageIDs.size(); i++) {
            if (flags.get(i).equalsIgnoreCase("Sent")) {
                foundSent = true;
                System.out.println("ID: " + messageIDs.get(i));
                System.out.println("To/By: " + recipientsOrDevelopers.get(i));
                System.out.println("Message: \"" + messageTexts.get(i) + "\"");
                System.out.println("Hash: " + messageHashes.get(i));
                System.out.println("------------------------------------");
            }
        }

        if (!foundSent) {
            System.out.println("No outgoing sent data records currently found.");
        }
    }

    // --- ASSIGNMENT ALGORITHMS ---

    public String getLongestMessage() {
        if (messageTexts.isEmpty()) return "No messages available.";
        String longest = messageTexts.get(0);
        for (String text : messageTexts) {
            if (text.length() > longest.length()) {
                longest = text;
            }
        }
        return longest;
    }

    public String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                return "\"" + messageTexts.get(i) + "\"";
            }
        }
        return "Message ID not found.";
    }

    public String searchByRecipient(String recipient) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < recipientsOrDevelopers.size(); i++) {
            if (recipientsOrDevelopers.get(i).equals(recipient)) {
                result.append("\"").append(messageTexts.get(i)).append("\" ");
            }
        }
        return result.toString().trim().isEmpty() ? "No messages found for this recipient." : result.toString().trim();
    }

    public String deleteMessageByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equalsIgnoreCase(hash)) {
                String removedText = messageTexts.get(i);
                messageIDs.remove(i);
                recipientsOrDevelopers.remove(i);
                messageTexts.remove(i);
                flags.remove(i);
                messageHashes.remove(i);
                return "Message: \"" + removedText + "\" successfully deleted.";
            }
        }
        return "Hash code not found.";
    }

    public void displayReport() {
        System.out.println("\n=== DISPLAY REPORT ===");
        for (int i = 0; i < messageIDs.size(); i++) {
            if (flags.get(i).equalsIgnoreCase("Sent")) {
                System.out.println("Message Hash: " + messageHashes.get(i));
                System.out.println("Recipient: " + recipientsOrDevelopers.get(i));
                System.out.println("Message: " + messageTexts.get(i));
                System.out.println("------------------------------------");
            }
        }
    }
}
