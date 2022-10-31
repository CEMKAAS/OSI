import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Locale;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080);) { // стартуем сервер один(!) раз
            String city = null;
            while (true) { // в цикле(!) принимаем подключения
                try (Socket socket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {
                    System.out.println("Server Start");
                    out.println(city == null ? "???" : city);
                    String stringFromSocket = in.readLine();
                    if (city == null) {
                        city = stringFromSocket;
                        out.println("Ok,first city");
                    } else {
                        char aCharCity = city.toLowerCase().charAt(city.length() - 1);
                        char aCharstringFromSocket = stringFromSocket.toLowerCase().charAt(0);
                        if (aCharCity == aCharstringFromSocket) {
                            city = stringFromSocket;
                            out.println("Ok");
                        } else {
                            out.println("not ok");
                        }
                    }
                }
            }
        } catch (
                IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}