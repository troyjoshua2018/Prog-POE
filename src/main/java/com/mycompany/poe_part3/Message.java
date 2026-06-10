/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe_part3;

public class Message {
    private final int messageID;
    private final String senderPhone;
    private final String recipientPhone;
    private final String messageText;
    private final String messageHash;

    public Message(int messageID, String senderPhone, String recipientPhone, String messageText, String messageHash) {
        this.messageID = messageID;
        this.senderPhone = senderPhone;
        this.recipientPhone = recipientPhone;
        this.messageText = messageText;
        this.messageHash = messageHash;
    }

    public int getMessageID() { return messageID; }
    public String getSenderPhone() { return senderPhone; }
    public String getRecipientPhone() { return recipientPhone; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
}