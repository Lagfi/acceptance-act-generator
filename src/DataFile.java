import com.github.petrovich4j.Case;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class DataFile {
    public static final HashMap<String, String> positionsMap = new HashMap<>();
    public static void loadPositionsDB(){
        String line;
        try {
            Path path = Paths.get("db_positions/positions.txt");
            BufferedReader reader = Files.newBufferedReader(path);
            while ((line = reader.readLine()) != null){
                line = line.trim();
                if (line.isEmpty() || !line.contains("=")){
                    continue;
                }
                String[] parts = line.split("=");
                String par = parts[0].trim().toLowerCase();
                String par2 = parts[1].trim();
                positionsMap.put(par , par2);
            }
            reader.close();

        }catch (Exception q){
            IO.println("Файл не найден");
        }
    }
    public static String getposition (String webPosition, Case myCase){
        String outWeb = webPosition.trim().toLowerCase();
        String result = positionsMap.get(outWeb);
        String[] genitiv;
        if (result != null){
            genitiv = result.split("\\|");
            if (myCase == Case.Dative) {
                return FioProcessor.fixRegister(genitiv[0]);
            }else if(myCase == Case.Genitive && genitiv.length >= 2){
                return FioProcessor.fixRegister(genitiv[1]);
            }else{return FioProcessor.fixRegister(genitiv[0]);}
        }
            return (FioProcessor.fixRegister(webPosition.trim()));
    }
}
