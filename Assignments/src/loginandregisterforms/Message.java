/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginandregisterforms;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public final class Message {


   private static int totalMessagesSent = 0;
    private static final List<Message> sentMessages = new ArrayList<>();
    
    private final String messageId;
    private final int numSent;
    private final String recipient;
    private final String messageContent;
    private final String messageHash;
    private boolean isSent;
    private boolean isStored;
    
    // Constructor
    public Message(String recipient, String messageContent) {
        this.messageId = generateMessageId();
        this.numSent = ++totalMessagesSent;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageHash = createMessageHash();
        this.isSent = false;
        this.isStored = false;
    }
    
    // Generate a random 10-digit message ID
    private String generateMessageId() {
        Random rand = new Random();
        long id = 1000000000L + rand.nextInt(900000000);
        return String.valueOf(id);
    }
    
    // Check if message ID is valid (10 digits)
    public boolean checkMessageId() {
        return this.messageId != null && this.messageId.length() == 10;
    }
    
    // Check recipient cell number format
    public int checkRecipientCell() {
        // Remove any non-digit characters
        String cleanNumber = this.recipient.replaceAll("[^0-9]", "");
        
        // Check if number starts with international code (assume + is present)
        if (this.recipient.startsWith("+") && cleanNumber.length() <= 15) {
            return 1; // Valid international number
        } 
        // Check for local number without international code
        else if (cleanNumber.length() == 10) {
            return 2; // Valid local number
        } else {
            return 0; // Invalid number
        }
    }
    
    // Create message hash
    public String createMessageHash() {
        String[] words = this.messageContent.split(" ");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length-1] : firstWord;
        
        return (this.messageId.substring(0, 2) + ":" + this.numSent + ":" + 
               firstWord.toUpperCase() + lastWord.toUpperCase());
    }
    
    // Handle sending, storing or disregarding message
    public String sentMessage() {
        String[] options = {"Send Message", "Disregard Message", "Store Message"};
        int choice = JOptionPane.showOptionDialog(null, 
            "What would you like to do with this message?", 
            "Message Options",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
            null, options, options[0]);
        
        switch(choice) {
            case 0 -> {
                // Send
                this.isSent = true;
                sentMessages.add(this);
                return "Message successfully sent.";
           }
            case 1 -> {
                // Disregard
                return "Press 0 to delete message.";
           }
            case 2 -> {
                // Store
                this.isStored = true;
                storeMessage();
                return "Message successfully stored.";
           }
            default -> {
                return "No action taken.";
           }
        }
    }
   
    // Store message in JSON format
    public void storeMessage() {
        JSONObject json = new JSONObject();
        json.put("messageId", this.messageId);
        json.put("numSent", this.numSent);
        json.put("recipient", this.recipient);
        json.put("messageContent", this.messageContent);
        json.put("messageHash", this.messageHash);
        
        // In a real app, you would write this to a file
        System.out.println("Stored message: " + json.toString());
    }
    
    // Print message details
    public String printMessage() {
        return "Message ID: " + this.messageId + "\n" +
               "Message Hash: " + this.messageHash + "\n" +
               "Recipient: " + this.recipient + "\n" +
               "Message: " + this.messageContent + "\n";
    }
    
    // Static method to get all sent messages
    public static String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message msg : sentMessages) {
            sb.append(msg.printMessage()).append("\n");
        }
        return sb.toString();
    }
    
    // Static method to get total messages sent
    public static int returnTotalMessages() {
        return totalMessagesSent;
    }
    
    // Getters
    public String getMessageId() { return messageId; }
    public String getRecipient() { return recipient; }
    public String getMessageContent() { return messageContent; }
    public String getMessageHash() { return messageHash; }
    public boolean isSent() { return isSent; }
    public boolean isStored() { return isStored; }

    Object displayMessage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static class JSONObject {

        public JSONObject() {
        }

        private void put(String messageId, String messageId0) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void put(String numSent, int numSent0) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
