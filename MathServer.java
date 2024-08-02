import java.io.*;
import java.net.*;
import java.util.*;

class MathServer {

    public static void main(String argv[]) throws Exception {
        String clientQuestion;
        String calculatedAnswer;

        ServerSocket welcomeSocket = new ServerSocket(6899);

        System.out.println("Attempting to connect");
        Socket connectionSocket = welcomeSocket.accept();
        System.out.println("Connection accepted");

        while (true) {


            BufferedReader inFromClient =
                    new BufferedReader(new
                            InputStreamReader(connectionSocket.getInputStream()));


            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            clientQuestion = inFromClient.readLine();
            System.out.println("Question = " + clientQuestion);
            calculatedAnswer = calculate(clientQuestion);


            outToClient.writeBytes(calculatedAnswer);

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
 

           