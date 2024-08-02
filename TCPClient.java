import java.io.*;
import java.net.*;

class TCPClient {

    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;

        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));

        // Get the local IP address of the client machine
        String clientName = InetAddress.getLocalHost().getHostAddress();

        Socket clientSocket = new Socket("127.0.0.1", 6899);

        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer =
                new BufferedReader(new
                        InputStreamReader(clientSocket.getInputStream()));

        // Send the client name as the first message to the server
        outToServer.writeBytes(clientName + '\n');

        // Read the acknowledgement message from the server
        String ackMessage = inFromServer.readLine();
        System.out.println("FROM SERVER: " + ackMessage);

        // Loop to repeatedly read input from the user and send it to the server
        while (true) {
            sentence = inFromUser.readLine();

            outToServer.writeBytes(sentence + '\n');

            // Check if the user entered "Close" to exit the loop
            if (sentence.equalsIgnoreCase("close")) {
                break;
            }

            modifiedSentence = inFromServer.readLine();

            System.out.println("FROM SERVER: " + modifiedSentence);

        }

        // Close the client socket before exiting the program
        clientSocket.close();
    }
}
