package loginandregisterforms;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import loginandregisterforms.Message;

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
                    JOptionPane.showMessageDialog(null, "Coming Soon.");
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

   
