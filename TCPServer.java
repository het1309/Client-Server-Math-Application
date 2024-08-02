import java.io.*;
import java.net.*;
import java.util.*;

class TCPServer {

    public static void main(String argv[]) throws Exception {

        String capitalizedSentence;

        ServerSocket welcomeSocket = new ServerSocket(6899);

        while(true) {
            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            // Read the client's name as the first message
            String clientName = inFromClient.readLine();

            // Send an acknowledgement message to the client
            String ackMessage = "You are now connected to the server. " +
                    "Enter a string or, enter a basic equation in the format a = x (+|-|*|/) y with a space between variables. "
                    + "Enter 'close' to close the connection.\r\n";
            outToClient.writeBytes(ackMessage);

            // Record the start time of the connection
            Date startTime = new Date();

            // Display the client's name and start time on the server
            System.out.println("Client connected: " + clientName + " at " + startTime);

            // Loop to process subsequent messages from the client
            while (true) {
                // Read a message from the client
                String clientSentence = inFromClient.readLine();

                // Check if the client wants to close the connection
                if (clientSentence.equalsIgnoreCase("close")) {
                    // Close the connection socket
                    connectionSocket.close();
                    break;
                } else if (clientSentence.charAt(0) == 'a') {
                    String argument = clientSentence.substring(3); // get characters from index 3 to the end
                    String result = calculate(argument); // pass the substring to the calculate function
                    clientSentence = "a = " + result; // update the clientSentence string with the result
                }else{
                    clientSentence = clientSentence.toUpperCase() + '\n';
                }

                // Display the message sent by the client on the server
                System.out.println("Message from " + clientName + ": " + clientSentence);

                outToClient.writeBytes(clientSentence);

            }

            // Record the end time of the connection
            Date endTime = new Date();

            // Calculate the duration of the connection
            long duration = endTime.getTime() - startTime.getTime();

            // Display the duration of the connection on the server
            System.out.println("Client disconnected: " + clientName + " after " + duration + " ms");
        }

    }

    private static String calculate(String q) {

        double a;

        try {
            //System.out.println("Question = " + q);
            StringTokenizer st = new StringTokenizer(q);
            double x = Double.parseDouble(st.nextToken());
            String operator = st.nextToken();
            double y = Double.parseDouble(st.nextToken());

            switch (operator) {
                case "+":
                    a = x + y;
                    break;
                case "-":
                    a = x - y;
                    break;
                case "*":
                    a = x * y;
                    break;
                case "/":
                    a = x / y;
                    break;
                case "%":
                    a = x % y;
                default:
                    return "Invalid input. Please use the form: x (+ | - | * | / | % ) y with a space between variables" + "\n";
            }
            return Double.toString(a) + '\n';
        } catch (NumberFormatException ex) {
            return ("Invalid input. Please use the form: x (+ | - | * | / | % ) y with a space between variables" + "\n");
        }

    }
}
