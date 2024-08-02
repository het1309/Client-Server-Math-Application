import java.io.*;
import java.net.*;

class MathClient {

    public static void main(String argv[]) throws Exception
    {
        String question = "";
        String answer;

        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("127.0.0.1", 6899);

        DataOutputStream outToServer =
                new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer =
                new BufferedReader(new
                        InputStreamReader(clientSocket.getInputStream()));

        while(true) {
            System.out.println("Enter a basic equation in the format x (+|-|*|/) y with a space between variables, " +
                    "\nOr use 'exit' to terminate connection.");

            question = inFromUser.readLine();
            //System.out.println("Accepted");
            if (question.equalsIgnoreCase("exit")) {
                System.out.println("Terminating connection");
                break; // exit the loop if user enters "exit"
            }

            //System.out.println("Accepted2");
            outToServer.writeBytes(question + '\n');

            //System.out.println("Accepted3");
            answer = inFromServer.readLine();


            //System.out.println("Accepted4");
            System.out.println("FROM SERVER: " + answer);


        }

        clientSocket.close();
    }
}

        