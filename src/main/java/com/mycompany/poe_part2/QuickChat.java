/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_part2;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuickChat {

    private final ArrayList<Message> messages = new ArrayList<>();
    private final ArrayList<Message> storedMessages = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    private String currentUser;
    private String currentPhone;

    // ================= START =================

    public void start(String username, String phoneNumber) {

        this.currentUser = username;
        this.currentPhone = phoneNumber;

        loadMessages();
        loadStoredMessages();

        startMenu();
    }

    // ================= MAIN MENU =================

    private void startMenu() {

        System.out.println("\n=== QUICKCHAT MENU ===");

        while (true) {

            System.out.println("\n1. Send messages");
            System.out.println("2. Show sent messages");
            System.out.println("3. Quit");

            System.out.print("Choose an option: ");

            int choice;

            while (!scanner.hasNextInt()) {
                System.out.print("Enter a valid option: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    handleSendFlow();
                    break;

                case 2:
                    showSentMessages();
                    break;

                case 3:
                    saveMessages();
                    saveStoredMessages();
                    System.out.println("Bye");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // ================= SHOW MESSAGES =================

    private void showSentMessages() {

        if (messages.isEmpty()) {
            System.out.println("\nNo messages sent yet.");
            return;
        }

        System.out.println("\n=== SENT MESSAGES ===");

        for (Message msg : messages) {

            System.out.println("\nMessage ID: " + msg.getMessageID());
            System.out.println("Sender: " + msg.getSenderPhone());
            System.out.println("Recipient: " + msg.getRecipientPhone());
            System.out.println("Message: " + msg.getMessageText());
            System.out.println("Message Hash: " + msg.getMessageHash());
        }
    }

    // ================= SEND FLOW =================

    private void handleSendFlow() {

        System.out.print("How many messages would you like to send this session? ");

        int limit;

        while (!scanner.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            scanner.next();
        }

        limit = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < limit; i++) {
            sendMessage(i + 1, limit);
        }

        saveMessages();
        saveStoredMessages();

        System.out.println("\nSession complete!");
    }

    // ================= SEND MESSAGE =================

    private void sendMessage(int current, int total) {

        System.out.println("\nMessage " + current + " of " + total);

        System.out.print("Recipient phone (+27XXXXXXXXX): ");
        String recipient = scanner.nextLine();

        if (!recipient.matches("^\\+27\\d{9}$")) {
            System.out.println("Invalid recipient number. Message skipped.");
            return;
        }

        System.out.print("Message: ");
        String text = scanner.nextLine();

        String hash;

        try {
            hash = generateHash(text);
        } catch (Exception e) {
            System.out.println("Hash error. Message skipped.");
            return;
        }

        // Generate random message ID
        int messageID = generateMessageID();

        // Display details
        System.out.println("\n=== MESSAGE DETAILS ===");
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + hash);

        Message msg = new Message(
                messageID,
                currentPhone,
                recipient,
                text,
                hash
        );

        System.out.println("\nChoose action:");
        System.out.println("1. Send message");
        System.out.println("2. Discard message");
        System.out.println("3. Store message (draft)");

        int choice;

        while (!scanner.hasNextInt()) {
            System.out.print("Enter valid option: ");
            scanner.next();
        }

        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {

            case 1:
                messages.add(msg);
                System.out.println("Message sent.");
                break;

            case 2:
                System.out.println("Message discarded.");
                break;

            case 3:
                storedMessages.add(msg);
                saveStoredMessages();
                System.out.println("Message stored as draft.");
                break;

            default:
                System.out.println("Invalid option. Message discarded.");
        }
    }

    // ================= GENERATE MESSAGE ID =================

    private int generateMessageID() {

        Random random = new Random();

        return 100000 + random.nextInt(900000);
    }

    // ================= SAVE SENT =================

    private void saveMessages() {

        try (PrintWriter writer = new PrintWriter(new FileWriter("messages.txt"))) {

            for (Message msg : messages) {

                writer.println(msg.getMessageID());
                writer.println(msg.getSenderPhone());
                writer.println(msg.getRecipientPhone());
                writer.println(msg.getMessageText());
                writer.println(msg.getMessageHash());
                writer.println("---");
            }

        } catch (IOException e) {
            System.out.println("Error saving messages.");
        }
    }

    // ================= LOAD SENT =================

    private void loadMessages() {

        File file = new File("messages.txt");

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                int id = Integer.parseInt(line);
                String sender = reader.readLine();
                String recipient = reader.readLine();
                String text = reader.readLine();
                String hash = reader.readLine();

                reader.readLine();

                Message msg = new Message(
                        id,
                        sender,
                        recipient,
                        text,
                        hash
                );

                messages.add(msg);
            }

        } catch (IOException e) {
            System.out.println("Error loading messages.");
        }
    }

    // ================= SAVE STORED =================

    private void saveStoredMessages() {

        try (PrintWriter writer = new PrintWriter(new FileWriter("stored_messages.txt"))) {

            for (Message msg : storedMessages) {

                writer.println(msg.getMessageID());
                writer.println(msg.getSenderPhone());
                writer.println(msg.getRecipientPhone());
                writer.println(msg.getMessageText());
                writer.println(msg.getMessageHash());
                writer.println("---");
            }

        } catch (IOException e) {
            System.out.println("Error saving stored messages.");
        }
    }

    // ================= LOAD STORED =================

    private void loadStoredMessages() {

        File file = new File("stored_messages.txt");

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                int id = Integer.parseInt(line);
                String sender = reader.readLine();
                String recipient = reader.readLine();
                String text = reader.readLine();
                String hash = reader.readLine();

                reader.readLine();

                Message msg = new Message(
                        id,
                        sender,
                        recipient,
                        text,
                        hash
                );

                storedMessages.add(msg);
            }

        } catch (IOException e) {
            System.out.println("Error loading stored messages.");
        }
    }

    // ================= HASH =================

    private static String generateHash(String message)
            throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(message.getBytes());

        StringBuilder hex = new StringBuilder();

        for (byte b : hash) {
            
            String h = Integer.toHexString(0xff & b);

            if (h.length() == 1) {
                hex.append('0');
            }

            hex.append(h);
        }

        return hex.toString();
    }
}