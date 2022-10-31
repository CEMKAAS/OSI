import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("localhost", 8080);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String serverResponse = in.readLine();
            Scanner scanner = new Scanner(System.in);
            System.out.println("The game has begun!");
            System.out.println(serverResponse);
            out.println(scanner.nextLine());
            String serverResponseTwo = in.readLine();
                System.out.println(serverResponseTwo);

        } catch (
                IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}

