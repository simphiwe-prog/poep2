/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poep2messages;

/**
 *
 * @author Student
 */
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class POEp2Messages {
    
    public void runMenu(boolean isLoggedin) {
        
        //Check login
        System.out.println("\nWelcome to Quick chat");
        System.out.print("\nHow many messages do you want to send");
        int numMessages = 0;
        
    }

   
    
    //Variables to store message info
    String messageID;
    String recipientNumber;
    String messageText;
    String messageHash;
    
    //Counts how many messages were sent
    int numMessagesSent = 0;
    
    //Array to store sent messages
    String[] sentMessageIDs = new String[100];
    String[] sentMessageHashes = new String[100];
    String[] sentMessageRecipients = new String[100];
    String[] sentMessageTexts = new String[100];
    int totalStored = 0;
    
    //Checks if method message ID is 10 characters or less
    public boolean checkMessageID() {
        if (messageID.length() <= 10) {
            return true;
        }else{
            return false;
        }
    }
    //Checks if the message is not more than 250 characters
    public String checkMessageLength() {
        if(messageText.length() <= 250) {
            return"Message ready to send.";
        }else{
            int extra = messageText.length() - 250;
            return "Message exceeds 250 characters by " + extra + "; please reduce the size.";
        }
    }
    
    //Checks if the recipient n umber has a + and is not too long
    public String checkRecipientCell(){
        if(recipientNumber.startsWith("+") && recipientNumber.length() <=13) {
            return"Cellphone number successfully captured.";
        }else{
            return"Cellphone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    //Cretes the message has
    public String createMessageHash(){
        String[] words = messageText.split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        lastWord = lastWord.replace("?", "");
        lastWord = lastWord.replace("!", "");
        lastWord = lastWord.replace(".", "");
        String idStart = messageID.substring(0, 2);
        String hash = idStart + ":" +numMessagesSent + ":" + firstWord + lastWord;
        hash = hash.toUpperCase();
        return hash;
    }
    
    //Lets the user send, discard or store the message
    public String SentMessage() {
        Scanner input = new Scanner(System.in);
        
        System.out.println("1 - Send Message");
        System.out.println("2 - Disregard Message");
        System.out.println("3 - Store Message");
        System.out.print("Enter option: ");
        String option = input.nextLine();
        
        if(option.equals("1")) {
            sentMessageIDs[totalStored] = messageID;
            sentMessageHashes[totalStored] = messageHash;
            sentMessageRecipients[totalStored] = recipientNumber;
            sentMessageTexts[totalStored] = messageText;
            totalStored++;
            numMessagesSent++;
            return "Message sent successfully.";
        }else if(option.equals("2")) {
            System.out.println("Press 0 to delete the message.");
            String confirm = input.nextLine();
            if (confirm.equals("0")) {
                return "Press 0 to delete the message.";
            }
            return "Message disregarded.";
        }else if(option.equals("3")){
            storeMessage();
            return "Message successfully stored.";
        }else{
            return "Invalid choice.";
        }
    }
    
    //Saves the message to a JSON file
    public void storeMessage() {
        String jsonText = "{\n"
        + " \"messageID\": \"" + messageID + "\",\n"
        + " \"messageHash\": \"" + messageHash + "\",\n"
        + " \"recipient\": \"" + recipientNumber + "\",\n"
        + "\"message\": \"" + messageText + "\",\n"
        + "}\n";
        
        try {
            try (FileWriter writer = new FileWriter("storedMessages.json" , true)) {
                writer.write(jsonText);
            }
            System.out.println("Message saved to storedMessages.json");
        } catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    //Prints all sent messages
    public String printMessages() {
        String allMessages = "";
        for (int i = 0; i < totalStored; i++) {
            allMessages = allMessages + "Message ID: " + sentMessageIDs[i] + "\n";
            allMessages = allMessages + "Hash: " + sentMessageHashes[i] + "\n";
            allMessages = allMessages + "Recipient: " + sentMessageRecipients[i] + "\n";
            allMessages = allMessages + "Message: " + sentMessageTexts[i] + "\n";
            allMessages = allMessages + "----------------" + "\n";
        }
        return allMessages;
    }
    
    //Returns total number of messages sent
    public int returnTotalMessages() {
        return numMessagesSent;
   
   
   }
    
    
}

