import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;


public class AppServer {

    public void server() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(63412), 0);
            server.createContext("/", new RootHandler());
            server.createContext("/create-act", new ActHandler());
            server.createContext("/style.css", new StyleHandler("style.css", "text/css"));
            server.createContext("/Animations.js", new StyleHandler("Animations.js", "application/javascript"));
            server.start();
            IO.println("Веб-сервер успешно запущен на порту 63412!");
        } catch (Exception e) {
            IO.println("Ошибка: " + e.getMessage());
        }
    }
}