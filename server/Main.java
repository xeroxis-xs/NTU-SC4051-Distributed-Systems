package server;

public class Main {

    public static void main(String[] args) {
        
        int serverPort = 12345;
        int BUFFER_SIZE = 1024;
        int HISTORY_SIZE = 100;
        boolean AT_MOST_ONCE = true;

		if (args.length > 0) {
			try {
				if (args.length == 1) {
					serverPort = Integer.parseInt(args[0]);
				}
                else {
                    System.err.println("Invalid arguments. Please try again.");
				    System.exit(1);
                }
			} catch (Exception e) {
				System.err.println("Invalid arguments. Please try again.");
				System.exit(1);
			}
		}

        if (AT_MOST_ONCE) {
            System.out.println("\nServer: Invocation Semantics: At Most Once");
            System.out.println("Server: History initialised with " + HISTORY_SIZE + " records capacity");
        } else {
            System.out.println("\nServer: Invocation Semantics: At Least Once");
            System.out.println("Server: No history is maintained");
        }

        Server server = new Server(BUFFER_SIZE, HISTORY_SIZE, AT_MOST_ONCE);
        server.listen(serverPort);

    }

}