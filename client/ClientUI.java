package client;

import utils.InputGetter;

public class ClientUI {

    private String serverIP;
    public ClientHandler clientHandler = new ClientHandler();
    public InputGetter ig = new InputGetter();
    public boolean isConnected = false;

    public void startClient() {
        int choice;

        do {
            System.out.println("+---------------------------------------+");
            System.out.println("|               Welcome!                |");
            System.out.println("+---------------------------------------+");
            System.out.println("| [1] Connect to a server               |");
            System.out.println("| [2] Exit                              |");
            System.out.println("+---------------------------------------+");
            System.out.print("\nEnter your choice: ");

            choice = ig.getInt();

            switch (choice) {
                case 1:
                    renderServerIP();
                    break;
                case 2:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 2);
    }

    public void startServices() {
        int choice;
        do {
            System.out.println("+---------------------------------------+");
            System.out.println("|             Service Menu              |");
            System.out.println("+---------------------------------------+");
            System.out.println("| [1] Read a content from a file        |");
            System.out.println("| [2] Insert a content into a file      |");
            System.out.println("| [3] Monitor updates of a file         |");
            System.out.println("| [4] Idempotent service                |");
            System.out.println("| [5] Non-idempotent service            |");
            System.out.println("| [6] Exit                              |");
            System.out.println("+---------------------------------------+");
            System.out.print("\nEnter your choice: ");
            choice = ig.getInt();

            switch (choice) {
                case 1:
                    startRead(1);
                    break;
                case 2:
                    startInsert(2);
                    break;
                case 3:
                    startMonitor(3);
                    break;
                case 4:
                    startIdempotent(4);
                    break;
                case 5:
                    startNonIdempotent(5);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);
    }

    private void renderServerIP() {

        System.out.print("\nEnter the IP address of the server: ");
        this.serverIP = ig.getString();

        System.out.println("You have selected to connect to " + this.serverIP);
        this.startConnection();

    }

    public String getServerIP() {
        return this.serverIP;
    }


    public void startConnection() {
        try {
            this.isConnected = this.clientHandler.connectToServer(this.serverIP);
            System.out.println("\nYou have successfully connected to " + this.serverIP + "!");
            this.startServices();
        }
        catch (Exception e) {
            // Return to main page
            System.out.println("\nConnection to " + this.serverIP + " failed. Please try again.");
        }
    }

    private void startRead(int messageHeader) {
        String message;
        boolean received = false;

        System.out.print("\nEnter the pathname of the file: ");
        String pathname = ig.getString();

        System.out.print("Enter the offset of the file content (in bytes) to read from: ");
        long offset = ig.getLong();

        System.out.print("Enter the number of bytes to read: ");
        int bytesToRead = ig.getInt();

        System.out.println("You have selected to read " + bytesToRead + " bytes from " + pathname + " starting from byte " + offset + ".");
        message = messageHeader + ":" + pathname + ":" + offset + ":" + bytesToRead;

        // Send requeest
        this.clientHandler.sendOverUDP(message);

        // Receive response
        while (!received) {
            received = this.clientHandler.receiveOverUDP();
        }
    }

    private void startInsert(int messageHeader) {

        System.out.print("\nEnter the pathname of the file: ");

        System.out.print("Enter the offset of the file content (in bytes) to insert into: ");

        System.out.print("Enter the number of bytes to insert: ");

        // System.out.println("You have selected to insert " + bytes + " bytes into " + pathname + " starting from byte " + offset + ".");
    }

    private void startMonitor(int messageHeader) {

    }

    private void startIdempotent(int messageHeader) {

    }

    private void startNonIdempotent(int messageHeader) {

    }
}

