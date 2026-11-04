import com.github.petrovich4j.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import static com.github.petrovich4j.NameType.PatronymicName;

class ActHandler implements HttpHandler {
    String content = "";
    String fullNameS = "";
    String fullNameR = "";
    String model = "";
    String serial = "";
    String jobTitle = "";
    String jobTitleR = "";
    private void parseRequestData(HttpExchange exchange) throws IOException{
        Scanner scanner = new Scanner(exchange.getRequestBody(), "UTF-8");
        String FioLing = scanner.nextLine();
        String clearText = URLDecoder.decode(FioLing, "UTF-8");
        System.out.println(clearText);
        String[] userData = clearText.split("&");
        for (String box : userData) {
            String[] pair = box.split("=");
            if (pair.length < 2) {
                continue;
            }
            switch (pair[0]) {
                case "fullNameS": fullNameS = pair[1];break;
                case "fullNameR": fullNameR = pair[1];break;
                case "jobTitle": jobTitle = pair[1];break;
                case "jobTitleR": jobTitleR = pair[1];break;
                case "model": model = pair[1];break;
                case "serial": serial = pair[1];break;
            }
            System.out.println(pair[1]);
        }
        scanner.close();
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            parseRequestData(exchange);
            Path templatePath = Path.of("autoDocs.xml");
            content = Files.readString(templatePath, StandardCharsets.UTF_8);
            String currentDateStr = Data.getDateRu();
            content = content.replace("$FIO_DATV$", FioProcessor.convertAndWriteFio(fullNameS, Case.Dative));
            content = content.replace("$FIO_SHORT$", FioProcessor.makeShort(fullNameS));
            content = content.replace("$FIO_GENITIVE$", FioProcessor.convertAndWriteFio(fullNameS, Case.Genitive));
            content = content.replace("$FIO_FULL$", fullNameS);
            content = content.replace("$FIO_FULLR$", fullNameR);
            content = content.replace("$FIO_DATV_REG$", FioProcessor.convertAndWriteFio(fullNameR, Case.Dative));
            content = content.replace("$FIO_SHORT_REG$", FioProcessor.makeShort(fullNameR));
            content = content.replace("$FIO_GENITIVE_REG$", FioProcessor.convertAndWriteFio(fullNameR, Case.Genitive));
            content = content.replace("$MODEL$", model);
            content = content.replace("$SERIAL$", serial);
            content = content.replace("$JOB_TITLE$", DataFile.getposition(jobTitle, Case.Dative));
            content = content.replace("$JOB_MAT$", DataFile.getposition(jobTitleR, Case.Genitive));
            content = content.replace("$DATE$", currentDateStr);
            sendResponseFile(exchange);
        }catch (Exception m){
            m.printStackTrace();
        }
    }
    private void sendResponseFile(HttpExchange exchange) throws IOException {
        try {
            byte[] fullDocs = content.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "text/xml; charset=UTF-8");
            exchange.getResponseHeaders().set("Content-Disposition", "attachment; filename=\"ActIssuance.doc\"");
            exchange.sendResponseHeaders(200, fullDocs.length);
            exchange.getResponseBody().write(fullDocs);
            exchange.getResponseBody().flush();
            exchange.getResponseBody().close();
            exchange.close();
        }catch (Exception q){
            System.out.println(q.getMessage());
        }
    }
}