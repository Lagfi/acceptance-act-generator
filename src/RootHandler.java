import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

    class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String webForm = Files.readString(Path.of("ProgramFace.html"));
            byte[] webFormByte = webForm.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, webFormByte.length);
            exchange.getResponseBody().write(webFormByte);
            exchange.getResponseBody().flush();
            exchange.close();
        }
    }
