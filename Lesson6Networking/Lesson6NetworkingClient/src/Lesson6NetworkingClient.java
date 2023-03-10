import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Lesson6NetworkingClient {

    public static void main(String[] args) {
        String server = null;
        int port = 0;
        boolean unknownParam = false;
        // Retrieve parameters from the terminal.
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--port")) port = Integer.parseInt(args[++i]);
            else if (args[i].equals("--server")) server = args[++i];
            else {
                System.out.printf("Invalid param! (Unknown %s)\n", args[i]);
                unknownParam = true;
            }
        }
        // Connect to the specified server through the specified port.
        if (port!=0 && server!=null && !unknownParam) {
            try (Socket s = new Socket()) {
                s.connect(new InetSocketAddress(server, port), 2000);
                try (InputStream inputStream = s.getInputStream();
                        Scanner scanner = new Scanner(inputStream)) {
                    while (scanner.hasNextLine()) {
                        System.out.println(scanner.nextLine());
                    }
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
