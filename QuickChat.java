 // Final submission
 // part 2 application.
 // StudentName:Memu Mukoma
 // This is the main entry point 
 // User input is handeled here
 import java.util.Scanner;

public class QuickChat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
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