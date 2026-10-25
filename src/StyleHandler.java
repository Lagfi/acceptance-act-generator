import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StyleHandler implements HttpHandler {

    private final String filePatch;
    private final String conntentType;
    public StyleHandler(String filePatch, String conntentType){
        this.filePatch = filePatch;
        this.conntentType = conntentType;
    }
    @Override
    public void handle (HttpExchange exchange) throws IOException{
        try {
            byte[] StyleWeb;
            Path myFile = Paths.get(filePatch);
            StyleWeb = Files.readAllBytes(myFile);
            exchange.getResponseHeaders().set("Content-Type", conntentType);
            exchange.sendResponseHeaders(200, StyleWeb.length);
            exchange.getResponseBody().write(StyleWeb);
            exchange.getResponseBody().flush();
            exchange.close();
        }catch (Exception q){
            System.out.println("Ошибка скачивания шаблона");
        }
    }
}
