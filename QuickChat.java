// Added parallel arrays for the messages
import java.util.Scanner;

public class QuickChat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // --- THE ARRAYS ---
        String[] sentMessages = new String[100];
        String[] disregardedMessages = new String[100];
        String[] storedMessages = new String[100];
        String[] messageHashes = new String[100];
        String[] messageIDs = new String[100];
        String[] recipients = new String[100];
        int arrayIndex = 0;
                 // Loading the data from the file into the arrays
        try {
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("messages.json"));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(","); 
                if (data.length >= 4) {
                    messageIDs[arrayIndex] = data[0].trim();
                    messageHashes[arrayIndex] = data[1].trim();
                    recipients[arrayIndex] = data[2].trim();
                    storedMessages[arrayIndex] = data[3].trim();
                    arrayIndex++;
                }
            }
            reader.close();
            System.out.println("Successfully loaded " + arrayIndex + " messages from the file.");
        } catch (Exception e) {
            System.out.println("No saved messages found. Starting with an empty list.");
        }
        // ------------------

        System.out.println("Welcome to QuickChat.");
        System.out.print("How many messages do you wish to enter? ");
        int numMessagesToEnter = scanner.nextInt();
        scanner.nextLine();

        int messagesEntered = 0;
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages");
            System.out.print("Choose an option: ");
            int menuOption = scanner.nextInt();
            scanner.nextLine();

            switch (menuOption) {
                case 1:
                    if (messagesEntered >= numMessagesToEnter) {
                        System.out.println("You have reached your limit of " + numMessagesToEnter + " messages.");
                        break;
                    }
                    
                    System.out.print("Enter recipient cell number: ");
                    String recipient = scanner.nextLine();

                    System.out.print("Enter message: ");
                    String text = scanner.nextLine();

                    Message msg = new Message(recipient, text);

                    String lengthResult = msg.checkMessageLength();
                    System.out.println(lengthResult);
                    
                    if (lengthResult.startsWith("Message exceeds")) {
                        break; 
                    }

                    System.out.println(msg.checkRecipientCell());
                    System.out.println("Message ID generated: " + msg.getMessageID());
                    System.out.println("Message Hash: " + msg.getMessageHash());

                    System.out.println("\nOptions:");
                    System.out.println("1 - Send Message");
                    System.out.println("2 - Disregard Message");
                    System.out.println("3 - Store Message to send later");
                    System.out.print("Choice: ");
                    int actionChoice = scanner.nextInt();
                    scanner.nextLine();

                    String actionResult = msg.sentMessage(actionChoice);
                    System.out.println(actionResult);
                    
                    // SAVING TO ARRAYS
                    recipients[arrayIndex] = recipient;
                    messageIDs[arrayIndex] = msg.getMessageID();
                    messageHashes[arrayIndex] = msg.getMessageHash();

                    if (actionChoice == 1) {
                        sentMessages[arrayIndex] = text;
                    } else if (actionChoice == 2) {
                        disregardedMessages[arrayIndex] = text;
                    } else if (actionChoice == 3) {
                        storedMessages[arrayIndex] = text;
                    }
                    arrayIndex++; 
                    messagesEntered++;
                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    running = false;
                    break;
                    
                

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        System.out.println("\nTotal messages sent: " + Message.returnTotalMessagess());
        System.out.println("\n--- All Sent Messages ---");
        System.out.println(Message.printMessages());

        scanner.close();
    }
}