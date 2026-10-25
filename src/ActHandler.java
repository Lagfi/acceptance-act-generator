import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

class ActHandler implements HttpHandler {
    String content = "";
    String gender = "";
    String genderReg = "";
    String lastName = "";
    String firstName = "";
    String middleName = "";
    String lastNameReg = "";
    String firstNameReg = "";
    String middleNameReg = "";
    String jobTitle = "";
    String model = "";
    String serial = "";
    // переменные для заполнения файла
    String dativeFio = "";
    String genitiveFio = "";
    String shortFio = "";
    String fullFio = "";
    String dativeFioReg = "";
    String genitiveFioReg = "";
    String shortFioReg = "";
    String fullFioReg = "";

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
                case "gender": gender = pair[1];break;
                case "genderReg": genderReg = pair[1];break;
                case "lastName": lastName = pair[1];break;
                case "firstName": firstName = pair[1];break;
                case "middleName": middleName = pair[1];break;
                case "lastNameReg": lastNameReg = pair[1];break;
                case "firstNameReg": firstNameReg = pair[1];break;
                case "middleNameReg": middleNameReg = pair[1];break;
                case "jobTitle": jobTitle = pair[1];break;
                case "model": model = pair[1];break;
                case "serial": serial = pair[1];break;
            }
            System.out.println(pair[1]);
        }
        scanner.close();
    }
    private void convertNamesToCases(){
        if (gender.equals("Женский")) {
            dativeFio = Women.makeDativeWoman(lastName, firstName, middleName);
            genitiveFio = Women.makeGenitiveWoman(lastName, firstName, middleName);
            shortFio = Women.makeShortWoman(lastName, firstName, middleName);
            fullFio = Women.makeFullWoman(lastName, firstName, middleName);
        } else if (gender.equals("Мужской")) {
            dativeFio = Man.makeDativeMan(lastName, firstName, middleName);
            genitiveFio = Man.makeGenitiveMan(lastName, firstName, middleName);
            shortFio = Man.makeShortMan(lastName, firstName, middleName);
            fullFio = Man.makeFullMan(lastName, firstName, middleName);
        }
        if (genderReg.equals("Женский")) {
            dativeFioReg = Women.makeDativeWoman(lastNameReg, firstNameReg, middleNameReg);
            genitiveFioReg = Women.makeGenitiveWoman(lastNameReg, firstNameReg, middleNameReg);
            shortFioReg = Women.makeShortWoman(lastNameReg, firstNameReg, middleNameReg);
            fullFioReg = Women.makeFullWoman(lastNameReg, firstNameReg, middleNameReg);
        } else if (genderReg.equals("Мужской")) {
            dativeFioReg = Man.makeDativeMan(lastNameReg, firstNameReg, middleNameReg);
            genitiveFioReg = Man.makeGenitiveMan(lastNameReg, firstNameReg, middleNameReg);
            shortFioReg = Man.makeShortMan(lastNameReg, firstNameReg, middleNameReg);
            fullFioReg = Man.makeFullMan(lastNameReg, firstNameReg, middleNameReg);
        }
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            parseRequestData(exchange);
            convertNamesToCases();
            Path templatePath = Path.of("autoDocs.fodt");
            content = Files.readString(templatePath, StandardCharsets.UTF_8);
            String currentDateStr = Data.getDateRu();
            content = content.replace("$FIO_DATV$", dativeFio);
            content = content.replace("$FIO_SHORT$", shortFio);
            content = content.replace("$FIO_GENITIVE$", genitiveFio);
            content = content.replace("$FIO_FULL$", fullFio);
            content = content.replace("$FIO_DATV_REG$", dativeFioReg);
            content = content.replace("$FIO_SHORT_REG$", shortFioReg);
            content = content.replace("$FIO_GENITIVE_REG$", genitiveFioReg);
            content = content.replace("$FIO_FULL_REG$", fullFioReg);
            content = content.replace("$MODEL$", model);
            content = content.replace("$SERIAL$", serial);
            content = content.replace("$JOB_TITLE$", jobTitle);
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
            exchange.getResponseHeaders().set("Content-Disposition", "attachment; filename=\"ActIssuance.odt\"");
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