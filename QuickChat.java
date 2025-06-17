package loginandregisterforms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_lab
 */
public class QuickChat {

    
    
    private static boolean isLoggedIn = false;
    
    public static void main(String[] args) {
        // Login simulation
        login();
        
        if (!isLoggedIn) {
            JOptionPane.showMessageDialog(null, "Login failed. Exiting application.");
            return;
        }
        
        // Welcome message
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat");
        
        // Main application loop
        boolean running = true;
        while (running) {
            String[] options = {"Send Messages", "Show recently sent messages", "Quit"};
            int choice = JOptionPane.showOptionDialog(null, 
                "Please select an option:", 
                "QuickChat Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, options, options[0]);
            
            switch(choice) {
                case 0 -> // Send Messages
                    sendMessages();
                case 1 -> // Show recent messages
                    JOptionPane.showMessageDialog(null, """
                                                        
                    Message Reports:
                    a. Display sender and recipent of all sent messages 
                    b. Display the longest sent message
                    c. Search for a message by recipient 
                    e. Delete a message vy hash 
                    f. Display full details of all sent messages"""); 
               case 2 -> {
                    // Quit
                    running = false;
                    JOptionPane.showMessageDialog(null,
                            "Total messages sent: " + Message.returnTotalMessages() +
                                    "\nThank you for using QuickChat!");
                }
                default -> {
                }     
            }
            
            
    private final List<String> sentMessages, = new ArrayList<> () ;
    private final List<String> disregardedMessages = new ArrayList<>() ;
    private final List<String> storedMessages = new ArrayList<>() ;
 
    private final Map<String, String[]> messageDetails = new HashMap<>();

    QuickChat(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    // Adds a message to the appropriate list based on its flag
    public void addMessage(String recipient, String message, String flag) {
        String messageId = generateMessageId();
        
        if (flag.equalsIgnoreCase("Sent")) {
            sentMessages.add("ID: " + messageId + ", Recipient: " + recipient + ", Message: " + message);
            messageDetails.put(messageId, new String[]{recipient, message});
        } else if (flag.equalsIgnoreCase("Stored")) {
            storedMessages.add(message);
            messageDetails.put(messageId, new String[]{recipient, message});
        } else if (flag.equalsIgnoreCase("Disregard")) {
            disregardedMessages.add(message);
        }
    }

    // Generates a unique message ID (For simplicity)
    private String generateMessageId() {
        return "MSG" + (sentMessages.size() + disregardedMessages.size() + storedMessages.size() + 1);
    }

    // Displays all sent messages
    public void displaySentMessages() {
        for (String msg : sentMessages) {
            System.out.println(msg);
        }
    }

    // Displays the longest message
    public String displayLongestMessage() {
        String longestMessage = "";
        for (String msg : sentMessages) {
            String messageContent = msg.split(", Message: ")[1];
            if (messageContent.length() > longestMessage.length()) {
                longestMessage = messageContent;
            }
        }
        return longestMessage;
    }

    // Search for a message by ID
    public void searchByMessageId(String messageId) {
        String[] details = messageDetails.get(messageId);
        if (details != null) {
            System.out.println("Recipient: " + details[0] + ", Message: " + details[1]);
        } else {
            System.out.println("Message ID not found.");
        }
    }

    // Search by recipient
    public void searchByRecipient(String recipient) {
        for (String msg : sentMessages) {
            if (msg.contains("Recipient: " + recipient)) {
                System.out.println(msg);
            }
        }
    }

    // Delete a message by ID
    public void deleteMessage(String messageId) {
        String[] details = messageDetails.remove(messageId);
        if (details != null) {
            sentMessages.removeIf(msg -> msg.contains("ID: " + messageId));
            System.out.println("Message successfully deleted: " + details[1]);
        } else {
            System.out.println("Message ID not found.");
        }
    }

    // Display report of sent messages
    public void displayReport() {
        System.out.println("Message Hash \t Recipient \t Message");
        for (String msg : sentMessages) {
            String[] parts = msg.split(", ");
            System.out.println(parts[0] + " \t" + parts[1] + " \t" + parts[2]);
            
                
            }
        }
   
    private static void login() {
        // Simple login simulation - in a real app, you'd have proper authentication
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        
        // Simple check - in reality, you'd verify against a database
        if (username != null && !username.isEmpty() && 
            password != null && !password.isEmpty()) {
            isLoggedIn = true;
        }
    }
    
    private static void sendMessages() {
        // Get number of messages to send
        String numMsgStr = JOptionPane.showInputDialog("How many messages would you like to send?");
        int numMessages;
        
        try {
            numMessages = Integer.parseInt(numMsgStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
            return;
        }
        
        // Send each message
        for (int i = 0; i < numMessages; i++) {
            String recipient = JOptionPane.showInputDialog("Enter recipient's phone number (include country code):");
            String message = JOptionPane.showInputDialog("Enter your message (max 250 characters):");
            
            // Validate message length
            if (message.length() > 250) {
                JOptionPane.showMessageDialog(null, 
                    "Please enter a message of less than 250 characters.");
                i--; // Retry this message
                continue;
            }
            
            // Create and process message
            Message msg = new Message(recipient, message);
            
            // Validate recipient number
            int recipientCheck = msg.checkRecipientCell();
            if (recipientCheck == 0) {
                JOptionPane.showMessageDialog(null, 
                    "Cell phone number is incorrectly formatted or does not contain an international code. " +
                    "Please correct the number and try again.");
                i--; // Retry this message
                continue;
            }
            
            // Process message (send, store, or disregard)
            String result = msg.sentMessage();
            JOptionPane.showMessageDialog(null, result);
            
            // If sent, show details
            if (msg.isSent()) {
                JOptionPane.showMessageDialog(null, msg.printMessage());
            }
        }
    }

}
   
