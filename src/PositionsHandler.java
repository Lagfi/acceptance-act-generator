import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.registry.LocateRegistry;
import java.util.Set;

public class PositionsHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        String method = "";
        exchange.getResponseBody();
        method = exchange.getRequestMethod();
        if (!method.equals("GET")){
            IO.println("Ошибка протокола: пришёл не GET");
        }
        Set<String> positions = DataFile.positionsMap.keySet();
        StringBuilder json = new StringBuilder();
        json.append("[");
        for(String post : positions){
            json.append("\"").append(post).append("\",");
        }
        json.deleteCharAt(json.length() - 1);
        json.append("]");
        byte[] responseByte = json.toString().getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, responseByte.length);
        exchange.getResponseBody().write(responseByte);
        exchange.getResponseBody().close();
    }
}
