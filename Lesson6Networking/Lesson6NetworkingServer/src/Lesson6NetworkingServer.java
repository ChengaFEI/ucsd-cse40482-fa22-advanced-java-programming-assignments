import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Lesson6NetworkingServer {

    public static void main(String[] args) {
        int port = 0;
        boolean unknownParam = false;
        // Retrieve parameters from the terminal.
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--port")) port = Integer.parseInt(args[++i]);
            else {
                System.out.printf("Invalid parameter! (Unknown: %s)\n", args[i]);
                unknownParam = true;
            }
        }
        // Start to listen the specified port.
        if (port!=0 && !unknownParam) {
            // Create a server socket.
            try (ServerSocket s = new ServerSocket(port)) {
                // Wait infinitely until a client connects.
                Socket incoming = s.accept();
                // Set up the output stream and send back the response to the client.
                try (OutputStream outputStream = incoming.getOutputStream();
                        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream,
                                StandardCharsets.UTF_8), true)) {
                    writer.println(
                            """
                            HTTP/1.0 200 OK
                            
                            Content-Type: text/html
                                                
                            <html>
                                <head>
                                    <title>Java Networking</title>
                                </head>
                                <body>
                                    <h1>Java Networking</h1>
                                </body>
                            </html>
                            """);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
