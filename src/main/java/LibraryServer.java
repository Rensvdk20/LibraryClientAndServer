import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LibraryServer {
    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.err.println("Usage: java LibraryServer <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String inputLine, outputLine;

            // Initiate conversation with client
            BookProtocol bookProtocol = new BookProtocol();
            outputLine = bookProtocol.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = bookProtocol.processInput(inputLine);
                out.println(outputLine);

                if (outputLine.equals("Thanks for using our ISBN searcher"))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
