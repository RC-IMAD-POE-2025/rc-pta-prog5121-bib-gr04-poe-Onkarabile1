/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loginandregisterforms;

/**
 *
 * @author RC_Student_lab
 */
public class QuickChatTest {
   public static void main(String[] args) {
        QuickChat app = new QuickChat(null);

        app.addMessage("+27384557896", "Did you get the cake?", "Sent");
        app.addMessage("+27388884567", "Where are you? You are late!", "Stored");
        app.addMessage("+27344884567", "Yohooo, I am at your gate.", "Disregard");
        app.addMessage("+08388884567", "It is dinner time!", "Sent");
        app.addMessage("+27388884567", "Ok, I am leaving without you.", "Stored");

        System.out.println("Sent Messages:");
        app.displaySentMessages();
        
        System.out.println("\nLongest Sent Message:");
        System.out.println(app.displayLongestMessage());

        System.out.println("\nSearch by Message ID (MSG2):");
        app.searchByMessageId("MSG2");

        System.out.println("\nSearch by Recipient (+27388884567):");
        app.searchByRecipient("+27388884567");

        System.out.println("\nDeleting message with ID MSG2:");
        app.deleteMessage("MSG2");

        System.out.println("\nDisplaying report:");
        app.displayReport();
    }
}
  

