import com.github.petrovich4j.Case;
import padeg.lib.Padeg;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class DataFile {
    public static final HashMap<String, String> positionsMap = new HashMap<>();
    public static final HashMap<String, String> molpositionsMap = new HashMap<>();
    public static void loadPositionsDB(){
        String line;
        String cleanPar = "";
        try {
            Path path = Paths.get("db_positions/positions.txt");
            BufferedReader reader = Files.newBufferedReader(path);
            while ((line = reader.readLine()) != null){
                line = line.trim();
                if (line.isEmpty()){
                    continue;
                }
                if (line.startsWith("*")){
                    cleanPar = line.substring(1).trim().toLowerCase();
                    molpositionsMap.put(cleanPar, cleanPar);
                }else {
                    cleanPar = line.trim().toLowerCase();
                    positionsMap.put(cleanPar, cleanPar);
                }
            }
            reader.close();

        }catch (Exception q){
            IO.println("Файл БД мат.ответсвенных не найден");
        }
    }
    public static String getposition (String webPosition, Case myCase){
        String padege = webPosition.trim();
        int padegNum = 1;
        String outWeb = webPosition.trim().toLowerCase();
        String result = positionsMap.get(outWeb);
        if (result == null) {
            result = molpositionsMap.get(outWeb);
        }
        if (result != null){
            if (myCase == Case.Dative){
                padegNum = 3;
            } else if (myCase == Case.Genitive) {
                padegNum = 2;
            }
            padege = Padeg.getAppointmentPadeg(result, padegNum);
        }
        return (FioProcessor.fixRegister(padege));
    }
}
